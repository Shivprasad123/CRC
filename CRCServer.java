import java.net.*;
import java.io.*;
import java.util.*;

class CRCServer
{
	public static void main(String[] args)throws IOException,NoSuchElementException,ArrayIndexOutOfBoundsException
	{
		Scanner sc = new Scanner(System.in);
		int pack_corr = 0;
		ServerSocket s1 = new ServerSocket(1234);
		System.out.println("Waiting for Connection");
			System.out.println();
		Socket ss = s1.accept();
		System.out.println("Connection Established");
			System.out.println();
		Random ran = new Random();
		for(int o=0;o<10;o++){
		System.out.println("------------------------------------");
		int si_data = 100;
		int si_code=33;
		int count2=0;
		int d[] = new int [si_data+si_code+1];
		int c[] = new int [si_code];
		//		while(true)
//		  while(count2 <=132){
		Scanner sc1 = new Scanner(ss.getInputStream());
		//number = sc.nextInt();
		String datastream = sc1.next();
		String generator = sc1.next(); 
		System.out.println("The Received dataWord :\n"+datastream);
			System.out.println();
		System.out.println("The received Codeword :\n"+generator);
			System.out.println();
		//int data[] = new int[datastream.length()];
		//int divisor[] = new int[generator.length()];

		for(int i=0;i<si_data+si_code-1;i++){
			d[i] = Integer.parseInt(datastream.charAt(i)+"");
//			System.out.print(d[i]+" ");
		}		
		d[0] = 1;

		//data[datastream.length()-2] = r.nextInt(2);

		for(int i =0;i<si_code;i++){
			c[i] = Integer.parseInt(generator.charAt(i)+"");
//			System.out.print(c[i]+" ");
		}
		//count2++;
		//}
		c[0] = 1;
		int temp[] = new int[si_data+si_code-1];
		int temp1[] = new int[si_data+si_code-1];

		for(int i=0;i<si_code;i++){
			temp1[i] = d[i];
		}
		int count = 0;
		int count1 = 0;
		int m =0;
		int j =0;
		int equal=0;
		for(int i=0;i<si_code;i++){
			if(d[i]==c[i]){
				equal++;
			}
		}
		//Calculation of remainder

		while(j<si_code && equal!=si_code){
			for(m=(0+j);m<(si_code+j);m++){
				temp[m] = (temp1[m] + c[m-j]) % 2;
				//					System.out.print(temp[m]+" ");
			}
			//					System.out.println();
			equal=0;
			j =0;
			count = 0;
			try{
			while(temp[j]==0){
				j++;
				count++;
			}
			}catch(Exception e){}
			if(j<si_code && equal!=si_code){
				for(int i=(0+j);i<(si_code+j);i++){
					if(temp[i]==c[i-j]){
						equal++;
					}
				}
				//if(j<si_code && equal!=si_code){
				count = count - count1;
				//	System.out.println("Count:"+count);
				for(int i=si_code+count1;i<=si_code+count;i++){
					if(i==si_data+si_code-1){
						break;
					}
					else{
						//			System.out.print("data:"+d[i]+" ");
						temp[i] = d[i];
					}
				}
				//	System.out.println();
				count1 =  count;
				for(int i=0;i<si_data+si_code-1;i++){
					temp1[i] = temp[i];
					//		System.out.print(temp[i]+" ");
				}
				//				System.out.println();
			}
			else{
				break;
			}
			//			for(int i=0;i<si_data+si_code-1;i++){
			//				System.out.print(temp[i]+" ");
			//			}
			//			System.out.println();
			}
			
			int k = 0;
			//System.out.println("The Original Dataword:");
			for(int i=si_data+si_code-2;i>si_data-1;i--){
				d[i] = temp[i];
				if(d[i] == 0){
					k++;
				}
			}
//			System.out.println("k"+k);
			PrintStream p = new PrintStream(ss.getOutputStream());
			
			if(k==si_code -1){
			System.out.println();
				System.out.println("The Original Dataword:");
				for(int i=0;i<si_data;i++){
					System.out.print(d[i]+" ");
				}
				p.println("DATA_Transfered_Successfully");
			System.out.println();
			}else{
				p.println("DATA_CORRUPTED");
				System.out.println("Data is Corrupted");
				pack_corr++;
			System.out.println();
			}


	}
			PrintStream p1 = new PrintStream(ss.getOutputStream());
			p1.println(pack_corr);
}}
