#ifdef GL_ES
#define LOWP lowp
    precision mediump float;
#else
    #define LOWP
#endif

varying vec4 v_color;
varying vec2 v_texCoords;

uniform sampler2D u_texture, u_texture_disp;
uniform mat4 u_projTrans;
uniform float u_dt;
uniform float u_u1, u_u2;
uniform float u_v1, u_v2;

void main() 
{
	float x = (v_texCoords.x - u_u1) / (u_u2 - u_u1);
	float y = (v_texCoords.y - u_v1) / (u_v2 - u_v1);
	float color_m =  sin(x * 10.0 * texture2D(u_texture_disp, v_texCoords / 2.0).r + u_dt) * 0.2;
	gl_FragColor = v_color * texture2D(u_texture, v_texCoords) * (1.2 + color_m);
}