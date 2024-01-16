#ifdef GL_ES
#define LOWP lowp
    precision mediump float;
#else
    #define LOWP
#endif

varying vec4 v_color;
varying vec2 v_texCoords;

uniform sampler2D u_texture;
uniform mat4 u_projTrans;
uniform float u_dt;
uniform float u_u1, u_u2;
uniform float u_v1, u_v2;

void main() 
{
	float x = (v_texCoords.x - u_u1) / (u_u2 - u_u1);
	float y = (v_texCoords.y - u_v1) / (u_v2 - u_v1);
	float m = 1.0 + y * 0.0;	
	float d = 0.0;
	if(x < 0.5) d = abs(0.5 - x - u_dt);
	else d = abs(x - 0.5 - u_dt);
	if(d <= 0.2)
		m += (0.2 - d) * 4.0;
	for(float i = 0.0; i < 5.0; i++)
		if(y >= ((1.0 / 11.0) * (i * 2.0)) && y <= ((1.0 / 11.0) * (i * 2.0 + 1.0)))
			m = 1.0;
	gl_FragColor = v_color * texture2D(u_texture, v_texCoords) * m;
}