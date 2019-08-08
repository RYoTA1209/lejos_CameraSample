package lejos_EV3_Camera;

import java.io.IOException;

import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.hardware.video.Video;
import lejos.hardware.video.YUYVImage;

public class CameraDemo {
	 public static void main(String[] args) {
         try {
//        	 Cameraの取得
             Video wc = BrickFinder.getDefault().getVideo();
//             Cameraをオープンする
//             (Videoオブジェクト).open(width,height)
             wc.open(160,120);
//            	フレームの取得（配列で返される）
             byte[] frame = wc.createFrame();
//             フレームをLCDに表示できる画像に変換
             YUYVImage img = new YUYVImage(frame,160, 120);
//             LCDの取得
             GraphicsLCD g = BrickFinder.getDefault().getGraphicsLCD();
             // Escapeが押されるまで
             while (!Button.ESCAPE.isDown()) {
            	 //frameの取得
                 wc.grabFrame(frame);
                 //gに表示
                 //YUYVImage.display(GraphicsLCD device,int xDest,int yDest,int threshold)
                 //device:表示するLCDディスプレイ
                 //xDest:表示するx座標
                 //yDest:表示するy座標
                 //threshold:しきい値
                 img.display(g, 0, 0, 128);
             }
             //カメラをクローズ
             //画面を消す
             wc.close();
             g.clear();
         } catch (IOException ioe) {
        	 //入出力エラー（カメラを正常に認識できない時のエラーなど）
             ioe.printStackTrace();
             System.out.println("Driver exception: " + ioe.getMessage());
         }
     }
}
