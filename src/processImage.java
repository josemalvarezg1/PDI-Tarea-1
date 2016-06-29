import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
       
/**
 *
 * @author José Manuel Alvarez García - CI 25038805
 */

public class processImage {

    private processImage() {}

    public static int imageHeight;
    public static int imageWidth;

    public static Image loadImage (String pathImageFile, Component c) throws FileNotFoundException {
        InputStream imageFile = new FileInputStream(pathImageFile);
        try {        
            Image image;
            byte bmpHeader[] = new byte[14];    //Cabecera del archivo bitmap
            imageFile.read(bmpHeader,0,14);
            byte bmpInfo[] = new byte[40];      //Información más específica del archivo bitmap
            imageFile.read(bmpInfo,0,40);
            imageWidth = (((int)bmpInfo[7]&0xff)<<24) | (((int)bmpInfo[6]&0xff)<<16) | (((int)bmpInfo[5]&0xff)<<8) | (int)bmpInfo[4]&0xff;  //Ancho de la imagen
            imageHeight = (((int)bmpInfo[11]&0xff)<<24) | (((int)bmpInfo[10]&0xff)<<16) | (((int)bmpInfo[9]&0xff)<<8) | (int)bmpInfo[8]&0xff;   //Alto de la imagen
            int bits = (((int)bmpInfo[15]&0xff)<<8) | (int)bmpInfo[14]&0xff;    //Cantidad de bits del archivo bitmap
            int imageSize = (((int)bmpInfo[23]&0xff)<<24) | (((int)bmpInfo[22]&0xff)<<16) | (((int)bmpInfo[21]&0xff)<<8) | (int)bmpInfo[20]&0xff;   //Tamaño de la imagen
            int colorsUsed = (((int)bmpInfo[35]&0xff)<<24) | (((int)bmpInfo[34]&0xff)<<16) | (((int)bmpInfo[33]&0xff)<<8) | (int)bmpInfo[32]&0xff;  //Cantidad de colores en caso de tener paleta
            switch (bits) {
                case 24:
                    {
                        //Se consideran 3 bytes por píxel
                        int imagePadding = (imageSize/imageHeight)-imageWidth*3;    //Relleno
                        int imageData[] = new int[imageHeight*imageWidth];  //Contenido de la imagen en int
                        byte imageBytes[] = new byte[(imageWidth+imagePadding)*3*imageHeight];  //Contenido de la imagen en bytes
                        imageFile.read(imageBytes,0,(imageWidth+imagePadding)*3*imageHeight);
                        int k = 0;
                        for (int j=0;j<imageHeight;j++) {
                            for (int i=0;i<imageWidth;i++) {
                                //Se va cargando píxel por píxel el contenido de la imagen                             
                                imageData[imageWidth*(imageHeight-j-1)+i] = (255&0xff)<<24 | (((int)imageBytes[k+2]&0xff)<<16) | (((int)imageBytes[k+1]&0xff)<<8) | (int)imageBytes[k]&0xff;
                                k += 3;
                            }
                            k += imagePadding;
                        }       
                        image = c.createImage(new MemoryImageSource(imageWidth,imageHeight,imageData,0,imageWidth));    //Se crea la imagen leída en formato Image
                        break;
                    }
                case 16:
                    {
                        //Se consideran 2 bytes por píxel
                        int imagePadding = (imageSize/imageHeight)-imageWidth*2;    //Relleno
                        int imageData[] = new int[imageHeight*imageWidth];  //Contenido de la imagen en int
                        byte imageBytes[] = new byte[(imageWidth+imagePadding)*2*imageHeight];  //Contenido de la imagen en bytes
                        imageFile.read(imageBytes,0,(imageWidth+imagePadding)*2*imageHeight);
                        int k = 0;
                        for (int j=0;j<imageHeight;j++) {
                            for (int i=0;i<imageWidth;i++) {
                                //Se va cargando píxel por píxel el contenido de la imagen 
                                imageData[imageWidth*(imageHeight-j-1)+i] = (255&0xff)<<24 | (((((int)imageBytes[k+1]>>>2)&0x3f)|0x60)<<3<<16) | ((((int)(((imageBytes[k+1]&0x3)<<3) | ((imageBytes[k]&0xe0)>>>5)))|0x60)<<3<<8) | ((((int)imageBytes[k]&0x1f)|0x60)<<3);
                                k += 2;
                            }
                            k += imagePadding;
                        }       
                        image = c.createImage(new MemoryImageSource(imageWidth,imageHeight,imageData,0,imageWidth));    //Se crea la imagen leída en formato Image
                        break;                    
                    }
                case 8:
                    {
                        int colors = 0;
                        if (colorsUsed > 0) colors = colorsUsed;    //Si no hay colores utilizados al leer la información del archivo, se consideran 256 para la paleta 
                        else colors = 256;
                        if (imageSize == 0) {
                            imageSize = ((((imageWidth*bits)+31)&~31)>>3);
                            imageSize *= imageHeight;
                        }
                        //Se consideran 4 bytes al leer la paleta
                        int imagePalette[] = new int[colors];
                        byte imagePaletteBytes[] = new byte[colors*4];
                        imageFile.read(imagePaletteBytes,0,colors*4);
                        int k = 0;
                        for (int i=0;i<colors;i++) {
                            imagePalette[i] = (255&0xff)<<24 | (((int)imagePaletteBytes[k+2]&0xff)<<16) | (((int)imagePaletteBytes[k+1]&0xff)<<8) | (int)imagePaletteBytes[k]&0xff;
                            k += 4;
                        }
                        //Se considera 1 byte por píxel                        
                        int imagePadding = (imageSize/imageHeight)-imageWidth;    //Relleno
                        int imageData[] = new int[imageWidth*imageHeight];  //Contenido de la imagen en int
                        byte imageDataBytes[] = new byte[(imageWidth+imagePadding)*imageHeight];  //Contenido de la imagen en bytes
                        imageFile.read(imageDataBytes,0,(imageWidth+imagePadding)*imageHeight);
                        k = 0;
                        for (int j=0;j<imageHeight;j++) {
                            for (int i=0;i<imageWidth;i++) {
                                //Se va cargando píxel por píxel el contenido de la imagen 
                                imageData[imageWidth*(imageHeight-j-1)+i] = imagePalette[((int)imageDataBytes[k]&0xff)];
                                k++;
                            }
                            k += imagePadding;
                        }       
                        image = c.createImage(new MemoryImageSource(imageWidth,imageHeight,imageData,0,imageWidth));    //Se crea la imagen leída en formato Image
                        break;   
                    }
                case 4:
                    {
                        int colors = 0;
                        if (colorsUsed > 0) colors = colorsUsed;    //Si no hay colores utilizados al leer la información del archivo, se consideran 16 para la paleta 
                        else colors = 16;
                        //Se consideran 4 bytes al leer la paleta
                        int imagePalette[] = new int[colors];
                        byte imagePaletteBytes[] = new byte[colors*4];
                        imageFile.read(imagePaletteBytes,0,colors*4);
                        int k = 0;
                        for (int i=0;i<colors;i++) {
                            imagePalette[i] = (255&0xff)<<24 | (((int)imagePaletteBytes[k+2]&0xff)<<16) | (((int)imagePaletteBytes[k+1]&0xff)<<8) | (int)imagePaletteBytes[k]&0xff;
                            k += 4;
                        }      
                        //Se considera 1 byte por dos píxeles
                        imageSize = (((imageWidth*bits)+31)&~31)>>3;
                        int imageData[] = new int[imageWidth*imageHeight];  //Contenido de la imagen en int
                        byte imageDataBytes[] = new byte[imageSize];  //Contenido de la imagen en bytes
                        k = 0;
                        for (int j=0;j<imageHeight;j++) {
                            imageFile.read(imageDataBytes,0,imageSize);
                            k = 0;
                            for (int i=0;i<imageWidth;i++) {
                                //Se va cargando píxel por píxel el contenido de la imagen 
                                if (imageWidth*(imageHeight-j-1)+i > imageWidth*imageHeight-1) break;
                                if (k > imageSize*imageHeight-1) break;
                                for (int l=0;l<2;l++) {
                                    if (l == 0) {
                                        imageData[imageWidth*(imageHeight-j-1)+i] = imagePalette[((int)(imageDataBytes[k]>>4)&0xf)];
                                        i++;
                                        if (i >= imageWidth) break;
                                    } else imageData[imageWidth*(imageHeight-j-1)+i] = imagePalette[((int)imageDataBytes[k]&0xf)];
                                }
                                k++;
                            }
                        }       
                        image = c.createImage(new MemoryImageSource(imageWidth,imageHeight,imageData,0,imageWidth));    //Se crea la imagen leída en formato Image
                        break;
                    }
                case 1:
                    {
                        int colors = 0;
                        if (colorsUsed > 0) colors = colorsUsed;    //Si no hay colores utilizados al leer la información del archivo, se consideran 2 para la paleta 
                        else colors = 2;
                        //Se consideran 4 bytes al leer la paleta
                        int imagePalette[] = new int[colors];
                        byte imagePaletteBytes[] = new byte[colors*4];
                        imageFile.read(imagePaletteBytes,0,colors*4);
                        int k = 0;
                        for (int i=0;i<colors;i++) {
                            imagePalette[i] = (255&0xff)<<24 | (((int)imagePaletteBytes[k+2]&0xff)<<16) | (((int)imagePaletteBytes[k+1]&0xff)<<8) | (int)imagePaletteBytes[k]&0xff;
                            k += 4;
                        }   
                        //Se considera 1 byte por ocho píxeles
                        imageSize = (((imageWidth*bits)+31)&~31)>>3;
                        int imageData[] = new int[imageWidth*imageHeight];  //Contenido de la imagen en int
                        byte imageDataBytes[] = new byte[imageSize];  //Contenido de la imagen en bytes
                        k = 0;
                        for (int j=0;j<imageHeight;j++) {
                            imageFile.read(imageDataBytes,0,imageSize);
                            k = 0;
                            for (int i=0;i<imageWidth; i++) {
                                //Se va cargando píxel por píxel el contenido de la imagen
                                if (imageWidth*(imageHeight-j-1)+i > imageWidth*imageHeight-1) break;
                                if (k > imageSize*imageHeight-1) break;
                                for (int l=0;l<8;l++) {
                                    imageData[imageWidth*(imageHeight-j-1)+i] = imagePalette[((int)(imageDataBytes[k]>>(8-l-1))&0x1)];
                                    if (l != 7) {
                                        i++;
                                        if (i >= imageWidth) break;
                                    }
                                }
                                k++;
                            }
                        }       
                        image = c.createImage(new MemoryImageSource(imageWidth,imageHeight,imageData,0,imageWidth));    //Se crea la imagen leída en formato Image
                        break;
                    }
                default:
                    JOptionPane.showMessageDialog(new Interfaz(),"El archivo cargado no es una imagen bitmap de 1, 4, 8, 16 o 24 bits.","Ha ocurrido un error",JOptionPane.ERROR_MESSAGE);        
                    image = (Image) null;
                    break;
            }
            imageFile.close();
            return image;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new Interfaz(),"Ha ocurrido un error cargando el bitmap.","Ha ocurrido un error",JOptionPane.ERROR_MESSAGE);        
        }
        return (Image) null;    
    }

    public static BufferedImage toBufferedImage(Image image) {
        //Se convierte la imagen de formato Image a BufferedImage
        if (image instanceof BufferedImage) return (BufferedImage) image;
        BufferedImage newImage = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
        Graphics2D aux = newImage.createGraphics();
        aux.drawImage(image,0,0,null);
        aux.dispose();
        return newImage;        
    }   
    
    public static int getHeight() {
        //Se obtiene el alto de la imagen
        return imageHeight;
    }

    public static int getWidth() {
        //Se obtiene el ancho de la imagen
        return imageWidth;
    }
    
    public static void setHeight(int height) {
        //Se asigna el alto de la imagen
        imageHeight = height;
    }

    public static void setWidth(int width) {
        //Se asigna el ancho de la imagen
        imageWidth = width;
    }
      
    public static BufferedImage flip90DegreesLeft(BufferedImage image){
        //Se gira la imagen 90 grados a la izquierda siendo trabajada como una matriz de píxeles
        int width = getWidth();
        int height = getHeight();
        BufferedImage flippedImage = new BufferedImage(height,width,image.getType());
        for (int i=0;i<width;i++) {
            for (int j=0;j<height;j++) {
                flippedImage.setRGB(j,width-i-1,image.getRGB(i,j));
            }
        }
        setWidth(height);
        setHeight(width);
        return flippedImage;         
    }
    
    public static BufferedImage flip270DegreesLeft(BufferedImage image){  
        //Se gira la imagen 270 grados a la izquierda
        return flip180Degrees(flip90DegreesLeft(image));        
    }
    
    public static BufferedImage flip90DegreesRight(BufferedImage image){ 
        //Se gira la imagen 90 grados a la derecha siendo trabajada como una matriz de píxeles
        int width = getWidth();
        int height = getHeight();
        BufferedImage flippedImage = new BufferedImage(height,width,image.getType());
        for (int i=0;i<width;i++) {
            for (int j=0; j<height;j++) {
                flippedImage.setRGB(height-j-1,i,image.getRGB(i,j));
            }
        }
        setWidth(height);
        setHeight(width);
        return flippedImage;
    }
    
    public static BufferedImage flip180Degrees(BufferedImage image){    
        //Se gira la imagen 180 grados 
        return flip90DegreesRight(flip90DegreesRight(image));        
    }
    
    public static BufferedImage flip270DegreesRight(BufferedImage image){    
        //Se gira la imagen 270 grados a la derecha        
        return flip180Degrees(flip90DegreesRight(image));        
    }
    
    public static BufferedImage invertImage(BufferedImage image) {
        //Se invierten los colores de la imagen siendo trabajada como una matriz de píxeles
        int width = getWidth();
        int height = getHeight();
        for (int i=0;i<width;i++) {
            for (int j=0;j<height;j++) {
                int pixel = image.getRGB(i,j);
                int alpha = (pixel>>24)&0xff;
                int red = (pixel>>16)&0xff;
                int green = (pixel>>8)&0xff;
                int blue = pixel&0xff;
                red = 255-red;
                green = 255-green;
                blue = 255-blue;
                pixel = (alpha<<24) | (red<<16) | (green<<8) | blue;
                image.setRGB(i,j,pixel);
            }
        }
        return image;        
    }  
    
    public static BufferedImage verticalFlip(BufferedImage image) {  
        //Se realiza el espejo vertical de la imagen siendo trabajada como una matriz de píxeles
        int width = getWidth();
        int height = getHeight()/2;
        for (int i=0;i<width;i++) {
            for (int j=0;j<height;j++) {
                int pixel = image.getRGB(i,j);
                image.setRGB(i,j,image.getRGB(i,image.getHeight()-j-1));
                image.setRGB(i,image.getHeight()-j-1,pixel);
            }
        }
        return image;        
    }
    
    public static BufferedImage horizontalFlip(BufferedImage image) {
        //Se realiza el espejo horizontal de la imagen siendo trabajada como una matriz de píxeles
        int width = getWidth()/2;
        int height = getHeight();
        for (int i=0;i<width;i++) {
            for (int j=0;j<height;j++) {
                int pixel = image.getRGB(i,j);
                image.setRGB(i,j,image.getRGB(image.getWidth()-i-1, j));
                image.setRGB(image.getWidth()-i-1,j,pixel);
            }
        }
        return image;        
    }

    public static void main(String[] args) { 
        Interfaz window = new Interfaz();
        window.setVisible(true);
    }
}
