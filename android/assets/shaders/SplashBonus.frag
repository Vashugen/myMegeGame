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
	float m = 1.0;	
	float d = 0.0;
	float d_center = sqrt((x-0.5)*(x-0.5)+(y-0.5)*(y-0.5));
	d = abs(d_center - u_dt);
	if(d <= 0.15)
		m += (0.15 - d) * 5.0;
	gl_FragColor = v_color * texture2D(u_texture, v_texCoords) * m;
}