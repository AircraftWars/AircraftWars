package game;

import java.util.Random;
import java.awt.image.BufferedImage;
/*
 * 
 * 
 * С�ɻ����У�   �̳з����ࣨ�����          ʵ�ֽӿ� ���� ��ý��� 
 * 
 * */
public class AirPlane extends FlyingObject implements Enemy{
	private ChecKpoint Checkpoints = new ChecKpoint();
	//	�ƶ��ٶ�
	 private int speed;

//	�ɻ�һ�����������Լ�������
	public AirPlane(){
//		��ȡͼƬ
		System.out.println("�ٶȣ�"+Checkpoints.ckt);
		

		image = Game.airplane;
//		��ȡͼƬ�Լ��Ĵ�С
		width = image.getWidth();
		height = image.getHeight();
//		ȡ����� ���С�ɻ�����
		Random rand = new Random();
//		���С�ɻ�����λ��   ��Ϸ��߼�ȥС�ɻ��Ŀ��
		x = rand.nextInt(Game.WIDTH - this.width);
		y = -this.height;
		life = 1;
		speed = 2;

	}
	/*
	 * ��ȡС�л���ͼƬ��״̬ΪLIFEʱ����С�л�ͼƬ
	 * ״̬ΪDEADʱ����4�ű���ͼƬ��ȫ�����غ�״̬��ΪREMOVE������null
	 */
	int index = 1;
	public BufferedImage getImage() {
		// if(life>0){
		// 	image = Game.airplane;
		// 	return Game.airplane;
		// }else if(life==0){
		// 	if(index==1){
		// 		index+=1;
		// 		image = Game.booms[0];
		// 		return Game.booms[0];
		// 	}else if(index==2){
		// 		index+=1;
		// 		image = Game.booms[1];
		// 		return Game.booms[1];
		// 	}else if(index==3){
		// 		index+=1;
		// 		image = Game.booms[2];
		// 		return Game.booms[2];
		// 	}else if(index==4){
		// 		index+=1;
		// 		image = Game.booms[3];
		// 		return Game.booms[3];
		// 	}else if(index==5){
		// 		state = REMOVE;
		// 		index = 1;
		// 		image = Game.airplane;
		// 		return null;
		// 	}
		// 	return Game.airplane;
		// }
		return null;
	}

	//	�����ӵ�
	public Bullet[] shoot(){
		Bullet[] res = new Bullet[1];
		res[0] = new Bullet(this.x+width/2,this.y+height+10,"down");


		return res;
	}
//	��д�÷ַ��� ���ܵ÷�
	public int getScore(){
		return 3;
	}

	//	��д�ƶ�����  ��������   y����
	public void step() {
		y+=speed;//����
		
	
	}
	//���緽��
	public boolean outOf() {
		
		
		return this.y>=Game.HEIGHT;
		
	}
	

}

