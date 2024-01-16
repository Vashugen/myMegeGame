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
uniform float u_light_distance;

void main() 
{
	float x = (v_texCoords.x - u_u1) / (u_u2 - u_u1);
	float m = 1.0;	
	float d = abs(x - u_dt);
	if(d <= u_light_distance)
		m += (u_light_distance - d) * 5.0;
	gl_FragColor = v_color * texture2D(u_texture, v_texCoords) * m;
}