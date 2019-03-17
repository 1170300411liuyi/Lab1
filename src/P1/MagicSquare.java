package P1;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
public class MagicSquare {
	public static void main(String args[]){
	    MagicSquare magic = new MagicSquare();
	    for(int a = 1 ; a<=5 ; a++)
	    {
	        if(magic.isLegalMagicSquare(String.valueOf(a)+".txt"))
	            System.out.println(a+":true");
	        else{
	            System.out.println(a+":false");
	        }
	    }
	}

	boolean isLegalMagicSquare(String fileName) {
		String filename = "src/P1/txt/" + fileName;//�����ļ�·��
	    String encoding = "UTF-8";//�ļ��ַ���������
	    String content = null;
	    File file = new File(filename);//�ļ�����
	    Long filelongth = file.length();
	    byte[] filecontent = new byte[filelongth.intValue()];
	    int horizontal = 0 , vertical = 0;
	    
	    try{
	        FileInputStream in = new FileInputStream(file);
	        in.read(filecontent);
	        in.close();
	    }catch(FileNotFoundException e){
	        e.printStackTrace();
	    }catch(IOException e){
	        e.printStackTrace();
	    }
	    
	    try{
	        content = new String(filecontent,encoding);
	    }catch(UnsupportedEncodingException e){
	        System.err.println("The OS does not support " + encoding);//ʵʱ���
	        e.printStackTrace();
	    }
	    String line[] = content.split("\n");
	    vertical = line.length;    //the rows of the square
	    horizontal = vertical;
	    int nums[][] = new int[vertical][vertical];
	    int sumx[] = new int[vertical];//���к�
	    int sumy[] = new int[horizontal];//���к�
	    int sumz[] = new int[2];//�Խ��ߺ�
	    //Ĭ�ϳ�ʼ��Ϊ0
	   for(int i = 0; i <vertical;i++)//�����ݽ��д����ж����ݵĺϷ��Բ������ֵ
	   {
	   if(line[i].split(".").length != 0)
	    {
	   System.out.println("���ݴ��ڸ�����");
	  return false;
	 }
	   if(line[i].split("-").length > 1)
	   {
	 System.out.println("���ݴ��ڸ���");
	  return false;
	 }
	              
	 String []data = line[i].split("\t");
	        if(data.length != vertical)
	           {
	             if(i == 0)
	                        System.out.println("����������");
	             else
	                      System.out.println("��Ϊ����");
	             return false;
	            }
	                
	          for(int j = 0; j < vertical ;j++)
	             {
	                 try{
	                      int num = Integer.valueOf(data[j]).intValue();
	                      nums[i][j]=num;
	                    }catch(NumberFormatException e){
	                        System.out.println("���ݴ��ڷǷ�����");
	                       return false;
	               }
	                    
	                    sumx[i] += nums[i][j];
	                   sumy[j] += nums[i][j];
	                  
	                   if(i == j)
	                   {
	                       sumz[0] += nums[i][j];
	                   }
	                   
	                   if(i + j +1 == horizontal)
	                   {
	                       sumz[1] += nums[i][j];
	                   }
	                }
	           }
	          
	            if(sumz[0] != sumz[1])
	           {
	                return false;
	           }
	           
	           int sum = sumz[0];
	           
	           for(int i = 0; i<vertical; i++)
	           {
	               if(sumx[i] != sum || sumy[i] != sum)
	                   return false;
	           }
			return true;
	            

		}
	public static boolean generateMagicSquare(int n) 
    {      
        int magic[][] = new int[n][n];
        int vertical = 0,horizontal= n / 2, i, j, square = n * n; 
        
        for (i = 1; i <= square; i++)
        {    
            magic[vertical][horizontal] = i;
            if (i % n == 0)
            	vertical++;
            else 
            {
                if (vertical == 0)
                	vertical = n - 1;
                else
                	vertical--;
                if (horizontal == (n - 1)) 
                	horizontal = 0;
                else    
                	horizontal++;
            }
        } 
         
        for (i = 0; i < n; i++)
        {
            for (j = 0; j < n; j++) {
                System.out.print(magic[i][j] + "\t");
            System.out.println(); 
            }
        }
        return true; 
    } 
	
	}

