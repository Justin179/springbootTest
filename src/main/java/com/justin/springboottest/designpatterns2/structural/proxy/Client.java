package com.justin.springboottest.designpatterns2.structural.proxy;

public class Client {
    /*
    只有第一次使用要load
     */
    public static void main(String[] args) {
        Image image = new ImageProxy("test_image.jpg");

        // 圖像還沒有被載入到內存中
        image.display();
        // 第一次調用 display 方法時，ImageProxy 將創建 RealImage 對象並加載圖像
        // Loading test_image.jpg
        // Displaying test_image.jpg

        // 第二次調用 display 方法時，直接顯示已加載的圖像，無需再次加載
        image.display();
        // Displaying test_image.jpg
    }
}
