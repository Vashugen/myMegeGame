package com.merge.game.scenes;

import com.merge.game.logic.Globals;
import com.merge.game.logic.Input;
import com.merge.game.logic.Tools;
import com.merge.game.logic.player_data.Player;
import com.merge.game.objects.Background;
import com.merge.game.objects.DisplayObject;
import com.merge.game.objects.GameObjectType;
import com.merge.game.objects.game_elements.Task;
import com.merge.game.objects.game_elements.Trash;
import com.merge.game.objects.grid.GenerateItemType;
import com.merge.game.objects.grid.GridCell;
import com.merge.game.objects.grid.GridObject;
import com.merge.game.objects.grid.MergeItem;
import com.merge.game.objects.gui.WindowGui;
import com.merge.game.objects.gui.elements.Button;
import com.merge.game.objects.gui.elements.buttons.bonus.BonusButton;
import com.merge.game.objects.gui.elements.buttons.bonus.BonusType;
import com.merge.game.objects.gui.elements.panels.info.LeftPanel;
import com.merge.game.objects.gui.elements.panels.RightPanel;
import com.merge.game.objects.gui.elements.panels.TaskItem;
import com.merge.game.objects.gui.windows.WindowShop;
import com.merge.game.resources.GameSound;
import com.merge.game.resources.textures.TextureItems;
import com.merge.game.resources.textures.Textures;

import java.util.ArrayList;

public class SceneGame extends Scene {

    private static final int GRID_COUNT_WIDTH = 9;
    private static final int GRID_COUNT_HEIGHT = 7; //7

    private static final int ITEM_START_COUNT = 21;

    private static final float GRID_COEF_WIDTH = 0.7f;
    private static final float GRID_COEF_HEIGHT = 0.7f;

    private Background _background;
    private static float gridWidth, gridHeight;

    private GridCell[][] _grid;
    private MergeItem[][] _items;
    private MergeItem _activeObject = null;

    private DisplayObject _panelBottom;
    private RightPanel _panelRight;
    private LeftPanel _panelLeft;

    private int _scoreCount;
    private int _goldCount;
    private int _levelCount;

    private Trash _trash;
    private Button _clearButton;

    private boolean _isBonusActivated = false;
    private int _activeBonusType = 0;
    private ArrayList<BonusButton> _buttonsBonus = new ArrayList<>();

    private ArrayList<Task> addedTasks = new ArrayList<>();

    public SceneGame() {
        GameSound.playBackgroundMusic(GameSound.mainTheme);
        initGameSettings();
        initBackground();
        initGrid();
        initItems();
        initPanels();
        initBonus();
        initTrash();
        initClear();
        initTasks();
    }

    public void update() {
        super.update();
        if (!WindowGui.get().isEmpty()){
            return;
        }
        updateEffects();
        updateActiveObject();
        updateItems();
        updateTopPanel();
        updatePanelInfo();
        updateTasks();
        updateClearButton();
        updateBonusButtons();
        updateBonusMagicGenerator();
        updateGlobal();
    }

    private void updateEffects() {

    }

    private void updateActiveObject() {

        if (_activeObject == null) {
            for (int i = 0; i < GRID_COUNT_WIDTH; i++) {
                for (int j = 0; j < GRID_COUNT_HEIGHT; j++) {
                    if (_items[i][j] != null && _items[i][j].isTouched()) {
                        activateObject(_items[i][j]);
                        _activeObject.startDrag();
                    }
                }
            }
        } else {
            if(_activeObject.getDistance() >= Globals.itemSize * 0.8f){
                _activeObject.setCenterPosition(Input.getTouchX(), Input.getTouchY());
                _activeObject.updateDragging();
            }
        }
    }

    private void activateBonus(int bonusType) {

        if(_isBonusActivated){
            deactivateBonus();
        }

        if(Player.get().getBonusCount(bonusType) <= 0){
            WindowGui.get().addWindow(new WindowShop(true));
            //WindowGui.get().addWindow(new WindowText("Error!", Fonts.fontMedium, "There are no bonus"));
            return;
        }

        Player.get().changeItem(bonusType, -1);
        _activeBonusType = bonusType;

        for (BonusButton buttonsBonus : _buttonsBonus) {
            if(buttonsBonus.getBonusType() == bonusType){
                buttonsBonus.setActive(true);
            }
        }

        _isBonusActivated = true;
    }

    private void deactivateBonus() {
        for (BonusButton buttonsBonus : _buttonsBonus) {
            if(buttonsBonus.getBonusType() == _activeBonusType){
                buttonsBonus.setActive(false);
            }
        }
        _isBonusActivated = false;
    }

    private void fixGenerator(MergeItem item) {
        if(item.getGameObjectType() == GameObjectType.GENERATE){
            item.fixGenerator();
        }
    }

    private void generateMaxLevelItems() {

        for (int i1 = 1; i1 <= BonusType.MAX_ITEM_CREATE_QUANTITY; i1++) {
            for (int i = 0; i < GRID_COUNT_WIDTH; i++) {
                for (int j = 0; j < GRID_COUNT_HEIGHT; j++) {
                    if (_items[i][j] == null) {
                        String generateType = getRandomGenerateType();
                        int level = getMaxLevel(generateType);
                        int type = Tools.randomInt(1, GenerateItemType.getTexture(generateType, level).length);
                        _items[i][j] = new MergeItem(_grid[i][j].getX(), _grid[i][j].getY(), _grid[i][j].getWidth(), _grid[i][j].getHeight(), i, j, type, level, generateType, GameObjectType.MERGE);
                        addChild(_items[i][j]);
                    }
                }
            }
        }
    }

    private void createRandomGenerator() {
        for (int i = 0; i < GRID_COUNT_WIDTH; i++) {
            for (int j = 0; j < GRID_COUNT_HEIGHT; j++) {
                if (_items[i][j] == null) {
                    _items[i][j] = new MergeItem(TextureItems.bonusRandomGenerator, _grid[i][j].getX(), _grid[i][j].getY(), _grid[i][j].getWidth(), _grid[i][j].getHeight(), i, j, 0, 1, GenerateItemType.MAGIC, GameObjectType.GENERATE);
                    _items[i][j].setType(GameObjectType.GENERATE);
                    addChild(_items[i][j]);
                }
            }
        }
    }

    private String getRandomGenerateType() {
        String result = "";
        do {
            int random = Tools.randomInt(0, Globals.generateExists.size());
            result = Globals.generateExists.get(random);
        }while (result == GenerateItemType.MAGIC);

        return result;
    }

    private int getMaxLevel(String generateType) {
        int level = 1;
        for (int i = 0; i < GRID_COUNT_WIDTH; i++) {
            for (int j = 0; j < GRID_COUNT_HEIGHT; j++) {
                if (_items[i][j] != null && _items[i][j].getGenerateType().equals(generateType) && !_items[i][j].isGenerate()) {
                    level = _items[i][j].getLevel() > level ? _items[i][j].getLevel() : level;
                }
            }
        }

        return level;
    }

    private void initGameSettings() {

        _scoreCount = Player.get().getScore();
        _levelCount = Player.get().getLevel();
        _goldCount = Player.get().getGold();

        gridWidth = Globals.screenWidth * GRID_COEF_WIDTH;
        gridHeight = Globals.screenHeight * GRID_COEF_HEIGHT;

        Globals.itemSize = (int) Math.min((gridWidth / GRID_COUNT_WIDTH), (gridHeight / GRID_COUNT_HEIGHT));

        //float maxW =

        Globals.offsetTop = Globals.screenHeight * 0.05f;
        Globals.offsetX = (Globals.screenWidth - (Globals.itemSize * GRID_COUNT_WIDTH)) / 2;
        Globals.offsetY = (Globals.screenHeight - (Globals.itemSize * GRID_COUNT_HEIGHT)) / 2;

        Globals.generateExists.add(GenerateItemType.KETTLE);
    }

    private void initBackground() {
        _background = new Background(Textures.TexBackgroundGame);
        addChild(_background);
    }

    private void initGrid() {
        initGridPanel();
        _grid = new GridCell[GRID_COUNT_WIDTH][GRID_COUNT_HEIGHT];
        for (int i = 0; i < GRID_COUNT_WIDTH; i++) {
            for (int j = 0; j < GRID_COUNT_HEIGHT; j++) {
                _grid[i][j] = new GridCell();
                addChild(_grid[i][j]);
                _grid[i][j].placeAtGrid(i, j);
            }
        }
    }

    private void initGridPanel() {
        DisplayObject gridPanel = new DisplayObject(TextureItems.gridPanel);
        addChild(gridPanel);
        gridPanel.setSize((GRID_COUNT_WIDTH * Globals.itemSize) * 1.02f, (GRID_COUNT_HEIGHT * Globals.itemSize) * 1.02f);
        gridPanel.setX(Globals.offsetX - gridPanel.getWidth() * 0.01f);
        gridPanel.setY(Globals.offsetTop - gridPanel.getHeight() * 0.01f);
    }

    private void initItems() {
        _items = new MergeItem[GRID_COUNT_WIDTH][GRID_COUNT_HEIGHT];
        initGenerateItem();
        //initMergeItems();
    }

    private void initPanels() {
        _panelRight = new RightPanel();
        addChild(_panelRight);
        _panelRight.init();

        _panelLeft = new LeftPanel();
        addChild(_panelLeft);
        _panelLeft.init();
    }

    private void initTrash() {
        _trash = new Trash(TextureItems.trash);
        _panelRight.getTrashPanel().addChild(_trash);
        _trash.init();
    }

    private void initClear() {
        _clearButton = new Button(TextureItems.clear);
        //_panelBottom.addClearButton(_clearButton);
    }

    private void initTasks() {
        for (int i = 0; i < Task.TASKS_COUNT; i++) {
            createTasks();
        }
    }

    private void createTasks() {
        int type, level, count;
        int tasksCount = Tools.randomInt(1, 3);
        for (int i = 0; i < tasksCount; i++){
            boolean createTask = true;
            String generateItemType = Globals.generateExists.get(Tools.randomInt(0, Globals.generateExists.size()));
            type = Tools.randomInt(1, TextureItems.kettle1.length);
            level = Tools.randomInt(0, MAX_LEVEL_QUANTITY) + 1;
            //level = Math.random() > 0.5 ? 2 : 3;
            count = Math.random() > 0.5 ? 1 : 2;
            Task task  = new Task(type, level, GenerateItemType.KETTLE, count);
            //проверка на дубли в заданиях, TODO check
            for(int j = 0; j < addedTasks.size(); j++){
                if(addedTasks.get(j).exists(task)){
                    createTask = false;
                    break;
                }
            }

            if(createTask){
                _panelLeft.setTask(task, i);
                addedTasks.add(task);
            }
        }
    }

    private void initBonus() {
        for (int i = 1; i <= BonusType.BONUS_QUANTITY; i++) {
            BonusButton bonusButton = new BonusButton(i);
            _buttonsBonus.add(bonusButton);
            _panelRight.addBonus(bonusButton, i);
        }
    }

    private void initGenerateItem() {
        int x = Tools.randomInt(GRID_COUNT_WIDTH);
        int y = Tools.randomInt(GRID_COUNT_HEIGHT);
        _items[x][y] = new MergeItem(_grid[x][y].getX(), _grid[x][y].getY(), _grid[x][y].getWidth(), _grid[x][y].getHeight(), x, y, 0, 1, GenerateItemType.KETTLE, GameObjectType.GENERATE);
        _items[x][y].setType(GameObjectType.GENERATE);
        addChild(_items[x][y]);
    }

    private void initMergeItems() {
        for (int i = 0; i < GRID_COUNT_WIDTH; i++) {
            for (int j = 0; j < GRID_COUNT_HEIGHT; j++) {
                if (_items[i][j] == null) {
                    int textureIndex = Tools.randomInt(1, TextureItems.kettle1.length);
                    //_items[i][j] = new MergeItem(TextureItems.kettleLevelOne[textureIndex], _grid[i][j].getX(), _grid[i][j].getY(), _grid[i][j].getWidth(), _grid[i][j].getHeight(), i, j, 1);
                    addChild(_items[i][j]);
                }
            }
        }
    }

    private void updateItems() {

        boolean isOverlap = false;
        if (!Input.isTouched() && _activeObject != null) {
            for (int i = 0; i < GRID_COUNT_WIDTH; i++) {
                for (int j = 0; j < GRID_COUNT_HEIGHT; j++) {
                    if (_grid[i][j].isMouseOver()) {
                        if(_isBonusActivated){
                            switch (_activeBonusType){
                                case BonusType.FIX_GENERATE:
                                    fixGenerator(_items[i][j]);
                                    deactivateBonus();
                                    break;
                            }
                        }else {
                            updateItem(i, j);
                            isOverlap = true;
                        }
                    }
                }
            }

            //проверяем пересечение с trash
            if (_trash.isMouseOver()) {
                putInTrash();
                _goldCount++;
            }

            if (!isOverlap) {
                returnActiveObject();
            }

            deactivateObject();
        }
    }

    private void activateObject(MergeItem obj) {
        //sound on activate
        _activeObject = obj;
        obj.setActive(true);
    }

    private void deactivateObject() {

        if (_activeObject == null) {
            return;
        }

        _activeObject.setActive(false);
        _activeObject.stopDragging();
        _activeObject = null;
    }

    private void updateTopPanel() {
        /*_panelBottom.getScorePanel().setLabel(_scoreCount);
        _panelBottom.getGoldPanel().setLabel(_goldCount);
        _panelBottom.getLevelPanel().setLabel(_levelCount);*/
    }

    private void updatePanelInfo() {
        _panelLeft.updateScore(_scoreCount);
        _panelLeft.updateGold(_goldCount);
    }

    private void updateTasks() {

        for (int i = 0; i < _panelLeft.getTasks().size(); i++) {

            TaskItem area = _panelLeft.getTasks().get(i);

            area.update();
            ArrayList<Task> addedTasks = area.getTaskPanel().getAddedTasks();

            //подсчёт количества подходящих для задания итемсов
            for (int j = 0; j < GRID_COUNT_WIDTH; j++) {
                for (int k = 0; k < GRID_COUNT_HEIGHT; k++) {
                    if (_items[j][k] != null && _items[j][k].canBeMerge()) {
                        for (int l = 0; l < addedTasks.size(); l++) {
                            if (addedTasks.get(l).exists(_items[j][k])) {
                                addedTasks.get(l).existsCount++;
                                addedTasks.get(l).itemsToRemove.add((_items[j][k]));
                            }
                        }
                    }
                }
            }

            if (area.tasksComplete()) {
                area.activeButton();
            } else {
                area.inactiveButton();
            }

            if (area.buttonIsPressed()) {
                //убираем с поля итемсы из задания
                for (int j = 0; j < addedTasks.size(); j++) {
                    Task currentTask = addedTasks.get(j);
                    for (int k = 0; k < currentTask.count; k++) {
                        GridObject itemToRemove = currentTask.itemsToRemove.get(k);
                        removeChild(itemToRemove);
                        _items[itemToRemove.getGridX()][itemToRemove.getGridY()] = null;
                    }
                }

                //меняем задание
                area.generateTask();

                //получаем золото - подсчёт в зависимости от уровня типа и пр
                //scoreCount ++;

                //проверка на баллы и расширение поля
            }
        }
    }

    private void updateClearButton() {
        if (_clearButton.isPressed()) {
            for (int i = 0; i < GRID_COUNT_WIDTH; i++) {
                for (int j = 0; j < GRID_COUNT_HEIGHT; j++) {
                    if (_items[i][j] != null && _items[i][j].canBeDeleted()) {
                        removeChild(_items[i][j]);
                        _items[i][j] = null;
                    }
                }
            }
        }
    }

    private void updateBonusButtons() {

        for (BonusButton bonusButton : _buttonsBonus) {
            if(bonusButton.isPressed()){
                int bonusType = bonusButton.getBonusType();
                if(_isBonusActivated){
                    Player.get().changeItem(_activeBonusType, 1);
                    if(_activeBonusType == bonusType){
                        deactivateBonus();
                    }else {
                        activateBonus(bonusType);
                    }
                }else {
                    activateBonus(bonusType);
                }
            }
        }
    }

    private void updateBonusMagicGenerator() {

        //1.если надо, создаём
        //2. обновляем его таймер
        //3. исчезает, если <=0

        if(_isBonusActivated && _activeBonusType == BonusType.MAGIC_GENERATOR){
            boolean bonusMagicGeneratorIsCreate= false;
            for (int i = 0; i < GRID_COUNT_WIDTH; i++) {
                for (int j = 0; j < GRID_COUNT_HEIGHT; j++) {
                    if (_items[i][j] == null && !bonusMagicGeneratorIsCreate) {
                        bonusMagicGeneratorIsCreate = true;
                        _items[i][j] = new MergeItem(_grid[i][j].getX(), _grid[i][j].getY(), _grid[i][j].getWidth(), _grid[i][j].getHeight(), i, j, 0, 1, GenerateItemType.MAGIC, GameObjectType.GENERATE);
                        _items[i][j].setType(GameObjectType.GENERATE);
                        addChild(_items[i][j]);
                        break;
                    }
                }
            }

            if(!bonusMagicGeneratorIsCreate){
                //если нет свободного места, пишем, что нет места, деактивируем бонус и возвращаем ему значение
            }

            deactivateBonus();
        }

        if(Globals.generatorExists(GenerateItemType.MAGIC)){
            for (int i = 0; i < GRID_COUNT_WIDTH; i++) {
                for (int j = 0; j < GRID_COUNT_HEIGHT; j++) {
                    if (_items[i][j] != null && _items[i][j].getGenerateType().equals(GenerateItemType.MAGIC) && _items[i][j].empty()) {
                        //удаляем генератор с поля
                        removeChild(_items[i][j]);
                        _items[i][j] = null;
                    }
                }
            }
        }
    }

    private void updateGlobal() {
        for (int i = 0; i < GRID_COUNT_WIDTH; i++) {
            for (int j = 0; j < GRID_COUNT_HEIGHT; j++) {
                if (_items[i][j] != null && _items[i][j].isGenerate()) {
                    Globals.updateGenerateList(_items[i][j].getGenerateType());
                }
            }
        }
    }

    private void putInTrash() {
        if(_activeObject.getGameObjectType() == GameObjectType.GENERATE && _activeObject.getGenerateType() == GenerateItemType.KETTLE){
            return;
        }
        removeChild(_activeObject);
        _items[_activeObject.getGridX()][_activeObject.getGridY()].destroy();
        _items[_activeObject.getGridX()][_activeObject.getGridY()] = null;
    }

    private void updateItem(int i, int j) {
        if (_activeObject.getGameObjectType() == GameObjectType.GENERATE) {
            updateGenerateItem(i, j);
        } else {
            updateMergeItem(i, j);
        }
    }

    private void returnActiveObject() {
        _activeObject.setX(_grid[_activeObject.getGridX()][_activeObject.getGridY()].getX());
        _activeObject.setY(_grid[_activeObject.getGridX()][_activeObject.getGridY()].getY());
    }

    private void updateGenerateItem(int i, int j) {
        
        //на том же месте
        if (_items[i][j] == _activeObject) {
            createItems();
            returnActiveObject();
        } else if (_items[i][j] == null) {
            moveItem(i, j);
        }
        //TODO this
//        else if(_items[i][j].canBeMerged() && _activeObject.canBeMerged) {
//
//        }
        else {
            returnActiveObject();
        }
    }

    private void updateMergeItem(int i, int j) {
        if (canBeMoved(i, j)) {
            moveItem(i, j);
        } else {
            returnActiveObject();
        }
    }

    private boolean canBeMoved(int i, int j) {

        if (_items[i][j] == null) {
            return true;
        }

        //сравнение ссылок
        if (_activeObject == _items[i][j]) {
            return false;
        }

        //не финальный уровень у итемсов
        if(_activeObject.isFinalLevel()){
            return false;
        }

        if (_activeObject.match(_items[i][j])) {
            return true;
        }

        return false;
    }

    private void moveItem(int i, int j) {

        removeChild(_items[i][j]);

        //generate items moved by this function too
        if (_activeObject.getGameObjectType() == GameObjectType.MERGE && _items[i][j] != null) {
            _activeObject.updateLevel();
        }

        _items[_activeObject.getGridX()][_activeObject.getGridY()] = null;
        _activeObject.placeAtGrid(i, j);
        //_activeObject.setGridPosition(i, j);
        //_activeObject.moveToGrid(i, j);
        _items[i][j] = _activeObject;
    }

    private void createItems() {
        //if(_activeObject.getMaxCount() > 0 && energyCount > 0){
        if (_scoreCount > 0 && !_activeObject.empty()) {
            boolean itemIsCreate = false;
            do {
                int x = Tools.randomInt(GRID_COUNT_WIDTH);
                int y = Tools.randomInt(GRID_COUNT_HEIGHT);
                if (_items[x][y] == null) {
                    if(_activeObject.getGenerateType() == GenerateItemType.MAGIC){
                        _items[x][y] = createMagicGeneratorItem(x, y);
                    }else if(_activeObject.getGenerateType() == GenerateItemType.POTION){
                        int level = Tools.randomInt(1, 3);
                        int type = Tools.randomInt(1, GenerateItemType.getTexture(_activeObject.getGenerateType(), 1).length);
                        _items[x][y] = new MergeItem(_grid[x][y].getX(), _grid[x][y].getY(), _grid[x][y].getWidth(), _grid[x][y].getHeight(), x, y, type, level, _activeObject.getGenerateType(), GameObjectType.MERGE);
                    }else {
                        int level = Tools.randomInt(1, 3);
                        int type = Tools.randomInt(1, GenerateItemType.getTexture(_activeObject.getGenerateType(), 1).length);
                        _items[x][y] = new MergeItem(_grid[x][y].getX(), _grid[x][y].getY(), _grid[x][y].getWidth(), _grid[x][y].getHeight(), x, y, type, level, _activeObject.getGenerateType(), GameObjectType.MERGE);
                    }
                    itemIsCreate = true;
                    _scoreCount--;
                    System.out.println(_activeObject.getEnergy() + "!");
                    _activeObject.decrementEnergy();
                    System.out.println(_activeObject.getEnergy() + "!!!");
                    addChild(_items[x][y]);
                }

            } while (!itemIsCreate);
        }
    }


    private MergeItem createMagicGeneratorItem(int i, int j) {
        String generateType = getRandomGenerateType();
        int levelMax = getMaxLevel(generateType);
        int level = Tools.randomInt((levelMax == 1 ? 1 : (levelMax - 1)), levelMax + 1);
        int type = Tools.randomInt(1, GenerateItemType.getTexture(generateType, level).length);
        return new MergeItem(_grid[i][j].getX(), _grid[i][j].getY(), _grid[i][j].getWidth(), _grid[i][j].getHeight(), i, j, type, level, generateType, GameObjectType.MERGE);
    }


}
