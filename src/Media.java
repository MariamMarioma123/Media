import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Media {
	public static void inverse(int[][] i) {
		for(int x=0;x<i.length;x++) {
			for(int j=0;j<i[x].length;j++) {
				i[x][j]=255-i[x][j];
			}
		}
		for (int x = 0; x < i.length; x++) {
			for (int j = 0; j < i[x].length; j++) {
				System.out.print(i[x][j] + " ");
			}
			System.out.println();
		}
		AssistantConv(i);
	}
	public static void avgfilter1(int[][] i,int s) {
		float x=1/(s*s);
		int[][] res=new int[i.length][i[0].length];
		int sum=0;
		int ns=s/2;
		for(int k=0;k<i.length;k++) {
			for(int z=0;z<i[k].length;z++) {
				sum=0;
				for(int j=-ns;j<=ns;j++) {
					for(int m=-ns;m<=ns;m++) {
						if(k+j>=0&&k+j<i.length&&z+m>=0&&z+m<i[0].length) {
						sum=(int) (sum+i[k+j][z+m]*x);
						}
						
					}
					
			
		}
				res[k][z]=sum;	
			}}
			 for (int k = 0; k < res.length; k++) {
					for (int j = 0; j < res[k].length; j++) {
						System.out.print(res[k][j] + " ");
					}
					System.out.println();
				}
		        AssistantConv(res);
			}
	
	public static void avgfilter(int [][] image, int s) {
        int ns = s / 2;
        int[][] newFiltered = new int[image.length][image[0].length];
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                float sum = 0;
                for (int vertical = -ns; vertical <= ns; vertical++) {
                    for (int horizontal = -ns; horizontal <= ns; horizontal++) {
                        if ((vertical + i) < 0 || (i + vertical ) >= image.length || (j + horizontal) < 0 || (j + horizontal ) >= image[0].length) {
                            sum+=0;
                        }
                        else{
                        sum += (image[i + vertical][j + horizontal] )/(s*s);
                            }}
                }
                newFiltered[i][j] = (int)sum;
            }
        }
        for (int i = 0; i < newFiltered.length; i++) {
			for (int j = 0; j < newFiltered[i].length; j++) {
				image[i][j]=newFiltered[i][j];
				System.out.print(newFiltered[i][j] + " ");
			}
			System.out.println();
		}
        AssistantConv(newFiltered);
        }
	
	public static void unisharpfilter(int [][] image, int s) {
        int ns = s / 2;
        int[][] newFiltered = new int[image.length][image[0].length];
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                float sum = 0;
                for (int vertical = -ns; vertical <= ns; vertical++) {
                    for (int horizontal = -ns; horizontal <= ns; horizontal++) {
                        int temp=0;
                        int temp2=0;
                        if ((vertical + i) < 0)
                            temp=i;
                        if((vertical+i)>=image.length)
                            temp=image.length-1;
                        if(horizontal+j>=image[i].length)
                            temp2= image[i].length-1;
                        if(horizontal+j<0)
                            temp2=j;
                        if(((j + horizontal) < 0 || (j + horizontal ) >= image[i].length) && !((vertical+i)>=image.length ||(vertical + i) < 0))
                            sum+=image[vertical+i][temp2]*-1;
                        else if(!((j + horizontal) < 0 || (j + horizontal ) >= image[i].length) &&((vertical+i)>=image.length ||(vertical + i) < 0))
                            sum+=image[temp][horizontal+j]*-1;
                        else if((horizontal+j<0 || horizontal+j>=image[i].length) && (vertical+i>=image.length || vertical+i<0))
                            sum+=image[temp][temp2]*-1;
                        else{
                            if(i+vertical==i && j+horizontal==j)
                                sum+=(image[i + vertical][j + horizontal])*(s*s);
                            else
                            sum += (image[i + vertical][j + horizontal])*-1;
                        }}
                }
                if((int)sum>=0) {
                	if((int)sum>=255) {
                		newFiltered[i][j]=255;
                	}
                	else
                		newFiltered[i][j] = (int)sum;}
                else
                	newFiltered[i][j] = 0;
            }
        }
        for (int i = 0; i < newFiltered.length; i++) {
			for (int j = 0; j < newFiltered[i].length; j++) {
				image[i][j]=newFiltered[i][j];
				System.out.print(newFiltered[i][j] + " ");
			}
			System.out.println();
        }
        AssistantConv(newFiltered);
        }
	
	
	public static void brighten(int[][] a, int v) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				if(a[i][j] + v<=255) {
				a[i][j] = a[i][j] + v;}
				else
					a[i][j]=255;

			}
		}
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
		AssistantConv(a);
	}

	public static void darken(int[][] a, int v) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				if(a[i][j] - v>=0) {
				a[i][j] = a[i][j] - v;}
				else
					a[i][j]=0;
			}
		}
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
		AssistantConv(a);

	}

	public static void AssistantConv(int[][] a) {
		final BufferedImage image = new BufferedImage(a.length, a[0].length, BufferedImage.TYPE_BYTE_GRAY);
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				Color mycolor= new Color(a[i][j],a[i][j],a[i][j]);
				image.setRGB(i, j, mycolor.getRGB());
				
			}
		}
		display(image);
	}
	public static BufferedImage converttogray(BufferedImage x){
		BufferedImage image= new BufferedImage(x.getWidth(),x.getHeight(),BufferedImage.TYPE_BYTE_GRAY);
		for(int i=0;i<x.getWidth();i++) {
			for(int j=0;j<x.getHeight();j++) {
				Color mycolor = new Color(x.getRGB(i, j));
				int red=(int) (mycolor.getRed()*0.299);
				int green=(int) (mycolor.getGreen()*0.587);
				int blue=(int) (mycolor.getBlue()*0.114);
				
				Color newColor = new Color(red+green+blue,
					    red+green+blue,red+green+blue);
				image.setRGB(i, j, newColor.getRGB());
			}
		}
		return image;
	
	}
 

	public static int[][] imgTo2D(BufferedImage image) {
		BufferedImage img = image;

		// get the image width and height
		int width = img.getWidth();
		int height = img.getHeight();

		// create a 2D array to hold the pixel values
		int[][] pixels = new int[width][height];

		// loop through each pixel and store the values in the array
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				Color mycolor= new Color(image.getRGB(j, i));
				int x= (int) (mycolor.getRed()*0.299+mycolor.getBlue()*0.114+mycolor.getGreen()*0.587);
				
				pixels[j][i] = x;
			}
		}
		return pixels;
	}

	public static void display(BufferedImage image) {
		// create a new JLabel to hold the image
		JLabel label = new JLabel(new ImageIcon(image));

		// create a new JFrame to display the image
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(label);
		frame.pack();
		frame.setVisible(true);
	}
	public static BufferedImage toBufferedImage(Image img)
	{
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}

	public static void main(String[] args) throws IOException {
		Image x=ImageIO.read(new File("RGB.jpg"));
		BufferedImage normalImage=  toBufferedImage(x);
		BufferedImage Grayscaled= converttogray(normalImage);
		int[][] arrayofimage= imgTo2D(Grayscaled);
		display(Grayscaled);
		brighten(arrayofimage,90);
		arrayofimage= imgTo2D(Grayscaled);
		darken(arrayofimage,40);
		arrayofimage= imgTo2D(Grayscaled);
		inverse(arrayofimage);
		arrayofimage= imgTo2D(Grayscaled);
		avgfilter(arrayofimage,3);
		arrayofimage= imgTo2D(Grayscaled);
		unisharpfilter(arrayofimage,3); 
		
	}
}
