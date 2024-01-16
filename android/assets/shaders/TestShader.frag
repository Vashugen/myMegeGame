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
	float m = 1.0 + abs(0.5 - u_dt);
	gl_FragColor = v_color * texture2D(u_texture, v_texCoords) * m;
}