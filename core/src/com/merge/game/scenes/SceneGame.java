package com.merge.game.scenes;

import com.badlogic.gdx.Gdx;
import com.merge.game.logic.Globals;
import com.merge.game.logic.Input;
import com.merge.game.logic.Tools;
import com.merge.game.objects.Background;
import com.merge.game.objects.game_elements.Task;
import com.merge.game.objects.game_elements.Trash;
import com.merge.game.objects.grid.GenerateItem;
import com.merge.game.objects.grid.GenerateItemType;
import com.merge.game.objects.grid.GridCell;
import com.merge.game.objects.grid.GridObject;
import com.merge.game.objects.GameObjectType;
import com.merge.game.objects.grid.MergeItem;
import com.merge.game.objects.gui.elements.panels.LeftPanel;
import com.merge.game.objects.gui.elements.panels.RightPanel;
import com.merge.game.objects.gui.elements.panels.TaskArea;
import com.merge.game.objects.gui.elements.panels.TopPanel;
import com.merge.game.resources.GameSound;
import com.merge.game.resources.textures.TextureItems;
import com.merge.game.resources.textures.Textures;

import java.util.ArrayList;

public class SceneGame extends Scene {

    private static final int GRID_COUNT_WIDTH = 9;
    private static final int GRID_COUNT_HEIGHT = 9; //7

    private static final int ITEM_START_COUNT = 21;

    private static final float GRID_COEF_WIDTH = 0.7f;
    private static final float GRID_COEF_HEIGHT = 0.7f;

    private Background _background;
    private static float gridWidth, gridHeight;

    private GridCell[][] _grid;
    private GridObject[][] _items;
    private GridObject _activeObject = null;

    private TopPanel _topPanel;
    private RightPanel _rightPanel;
    private LeftPanel _leftPanel;

    private int _scoreCount = 0;
    private int _goldCount = 0;
    private int _levelCount = 0;

    private Trash _trash;

    public SceneGame() {
        GameSound.playBackgroundMusic(GameSound.mainTheme);
        initGameSettings();
        initBackground();
        initGrid();
        initItems();
        initPanels();
        initTrash();
        initTasks();
    }

    public void update(){
        super.update();
        initActiveObject();
        updateItems();
        updateTopPanel();
        updateTaskPanel();
    }

    private void initGameSettings() {

        gridWidth = Globals.screenWidth * GRID_COEF_WIDTH;
        gridHeight = Globals.screenHeight * GRID_COEF_HEIGHT;

        Globals.itemSize = (int) Math.min((gridWidth / GRID_COUNT_WIDTH), (gridHeight / GRID_COUNT_HEIGHT));

        Globals.offsetX = (Globals.screenWidth - (Globals.itemSize * GRID_COUNT_WIDTH)) / 2;
        Globals.offsetY = (Globals.screenHeight - (Globals.itemSize * GRID_COUNT_HEIGHT)) / 2;
    }

    private void initBackground() {
        _background = new Background(Textures.TexBackgroundGame);
        addChild(_background);
    }

    private void initGrid() {
         _grid = new GridCell[GRID_COUNT_WIDTH][GRID_COUNT_HEIGHT];
        for (int i = 0; i < GRID_COUNT_WIDTH; i++) {
            for (int j = 0; j < GRID_COUNT_HEIGHT; j++) {
                _grid[i][j] = new GridCell();
                addChild(_grid[i][j]);
                _grid[i][j].placeAtGrid(i, j);
            }
        }
    }

    private void initItems(){
        _items = new GridObject[GRID_COUNT_WIDTH][GRID_COUNT_HEIGHT];
        initGenerateItem();
        //initMergeItems();
    }

    private void initPanels(){
        _topPanel = new TopPanel(_scoreCount, _goldCount, _levelCount);
        addChild(_topPanel);
        _topPanel.init();

        _rightPanel = new RightPanel();
        addChild(_rightPanel);
        _rightPanel.init();

        _leftPanel = new LeftPanel();
        addChild(_leftPanel);
        _leftPanel.init();
    }

    private void initTrash() {
        _trash = new Trash(TextureItems.trash);
        _rightPanel.getTrashPanel().addChild(_trash);
        _trash.init();
    }

    private void initTasks(){

    }

    private void initGenerateItem() {
        int x = Tools.randomInt(GRID_COUNT_WIDTH);
        int y = Tools.randomInt(GRID_COUNT_HEIGHT);
        _items[x][y] = new GenerateItem(TextureItems.kettle1[0], _grid[x][y].getX(), _grid[x][y].getY(), _grid[x][y].getWidth(), _grid[x][y].getHeight(), x, y, 1, 1, GenerateItemType.KETTLE);
        _items[x][y].setType(GameObjectType.GENERATE);
        addChild(_items[x][y]);
    }

    private void initMergeItems() {
        for (int i = 0; i < GRID_COUNT_WIDTH; i++) {
            for (int j = 0; j < GRID_COUNT_HEIGHT; j++) {
                if(_items[i][j] == null){
                    int textureIndex = Tools.randomInt(1, TextureItems.kettle1.length);
                    //_items[i][j] = new MergeItem(TextureItems.kettleLevelOne[textureIndex], _grid[i][j].getX(), _grid[i][j].getY(), _grid[i][j].getWidth(), _grid[i][j].getHeight(), i, j, 1);
                    addChild(_items[i][j]);
                }
            }
        }
    }

    private void initActiveObject() {
        if(_activeObject == null){
            for (int i = 0; i < GRID_COUNT_WIDTH; i++) {
                for (int j = 0; j < GRID_COUNT_HEIGHT; j++) {
                    if(_items[i][j] != null && _items[i][j].isTouched()){
                        if(_items[i][j].getType() == GameObjectType.GENERATE){
                            int a = 1;
                        }
                        _activeObject = _items[i][j].getGameObjectType() == GameObjectType.GENERATE ? (GenerateItem) _items[i][j] : (MergeItem) _items[i][j];
                    }
                }
            }
        }else{
            _activeObject.setCenterPosition(Gdx.input.getX(), Gdx.input.getY());
        }
    }

    private void updateItems() {
        boolean isOverlap = false;
        if(_activeObject != null && !Input.isTouched()){
            for (int i = 0; i < GRID_COUNT_WIDTH; i++) {
                for (int j = 0; j < GRID_COUNT_HEIGHT; j++) {
                    if(_grid[i][j].isMouseOver()){
                        updateItem(i, j);
                        isOverlap = true;
                    }
                }
            }

            //проверяем пересечение с trash
            if(_trash.isMouseOver()){
                putInTrash();
                _goldCount ++;
            }

            if(!isOverlap){
                returnActiveObject();
            }

            _activeObject = null;
        }
    }

    private void updateTopPanel() {
        _topPanel.getScorePanel().setLabel(_scoreCount);
        _topPanel.getGoldPanel().setLabel(_goldCount);
        _topPanel.getLevelPanel().setLabel(_levelCount);
    }

    private void updateTaskPanel(){

        for (int i = 0; i < _leftPanel.getTasks().size(); i++) {

            TaskArea area = _leftPanel.getTasks().get(i);

            area.update();
            ArrayList <Task> addedTasks = area.getTaskPanel().getAddedTasks();

            //подсчёт количества подходящих для задания итемсов
            for (int j = 0; j < GRID_COUNT_WIDTH; j++) {
                for (int k = 0; k < GRID_COUNT_HEIGHT; k++) {
                    if(_items[j][k] != null && _items[j][k].canBeMerge()){
                        for (int l = 0; l < addedTasks.size(); l++) {
                            if(addedTasks.get(l).exists(_items[j][k])){
                                addedTasks.get(l).existsCount++;
                                addedTasks.get(l).itemsToRemove.add((_items[j][k]));
                            }
                        }
                    }
                }
            }

            //проверка количества имеющихся на поле итемсов
            for (int j = 0; j < addedTasks.size(); j++) {
                if(addedTasks.get(j).existsCount >= addedTasks.get(j).count){
                    area.inactiveButton();
                }else {
                    area.activeButton();
                }
            }

            if(area.buttonIsPressed()){
                //убираем с поля итемсы из задания
                for (int j = 0; j < addedTasks.size(); j++) {
                    Task currentTask = addedTasks.get(i);
                    for (int k = 0; k < currentTask.count; k++) {
                        GridObject itemToRemove = currentTask.itemsToRemove.get(j);
                        removeChild(itemToRemove);
                        _items[itemToRemove.getGridX()][itemToRemove.getGridY()] = null;
                    }
                }

                //меняем задание
                area.generateTask();

                //подсчитываем баллы
                //scoreCount ++;

                //проверка на баллы и расширение поля
            }
        }
    }

    private void putInTrash() {
        removeChild(_activeObject);
        _items[_activeObject.getGridX()][_activeObject.getGridY()].destroy();
        _items[_activeObject.getGridX()][_activeObject.getGridY()] = null;
    }

    private void updateItem(int i, int j) {
        if(_activeObject.getGameObjectType() == GameObjectType.GENERATE){
            updateGenerateItem(i, j);
        }else{
            updateMergeItem(i, j);
        }
    }

    private void returnActiveObject() {
        _activeObject.setX(_grid[_activeObject.getGridX()][_activeObject.getGridY()].getX());
        _activeObject.setY(_grid[_activeObject.getGridX()][_activeObject.getGridY()].getY());
    }

    private void updateGenerateItem(int i, int j) {

        if(_items[i][j] == _activeObject){
            createItems(_activeObject.getGenerateType());
            returnActiveObject();
        }else if(_items[i][j] == null){
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
        if(canBeMoved(i, j)){
            moveItem(i, j);
        }else {
            returnActiveObject();
        }
    }

    private boolean canBeMoved(int i, int j) {

        if(_items[i][j] == null){
            return true;
        }

        //сравнение ссылок
        if(_activeObject == _items[i][j]){
            return false;
        }

        if(((MergeItem) _activeObject).match(_items[i][j])){
            return true;
        }

        //не финальный уровень у итемсов
        if(((MergeItem) _activeObject).isFinalLevel()){
            return false;
        }

        return false;
    }

    private void moveItem(int i, int j) {

        removeChild(_items[i][j]);

        //generate items moved by this function too
        if(_activeObject.getGameObjectType() == GameObjectType.MERGE && _items[i][j] != null){
            ((MergeItem)_activeObject).updateLevel();
        }

        _items[_activeObject.getGridX()][_activeObject.getGridY()] = null;
        _activeObject.placeAtGrid(i, j);
        _items[i][j] = _activeObject;

    }

    private void createItems(String generateType) {
        int energyCount = 10;
        //if(_activeObject.getMaxCount() > 0 && energyCount > 0){
        if(energyCount > 0){
            boolean itemIsCreate = false;
            do{
                int x = Tools.randomInt(GRID_COUNT_WIDTH);
                int y = Tools.randomInt(GRID_COUNT_HEIGHT);
                int index = Tools.randomInt(1, TextureItems.kettle1.length);
                if(_items[x][y] == null){
                    _items[x][y] = new MergeItem(TextureItems.kettle1[index], _grid[x][y].getX(), _grid[x][y].getY(), _grid[x][y].getWidth(), _grid[x][y].getHeight(), x, y, index, 1, generateType);
                    itemIsCreate = true;
                    energyCount --;
                    addChild(_items[x][y]);
                }

            }while (!itemIsCreate);
        }
    }


}
