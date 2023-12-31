package com.justin.springboottest.designpatterns2.structural.proxy;

// Image interface
public interface Image {
    void display();
}

// RealImage class implementing Image interface
class RealImage implements Image {
    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk(fileName);
    }

    private void loadFromDisk(String fileName) {
        System.out.println("Loading " + fileName);
    }

    @Override
    public void display() {
        System.out.println("Displaying " + fileName);
    }
}

// ImageProxy class acting as a proxy to RealImage
class ImageProxy implements Image {
    private String fileName;
    private RealImage realImage;

    public ImageProxy(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        // 只有在需要顯示圖像時才實例化 RealImage，這可以幫助我們延遲真正的對象創建，直到需要使用它為止。
        // 這樣，你就可以使用代理模式來控制對真實對象的訪問，並且可以實現一些額外的邏輯（例如延遲加載、權限控制等）。
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}















