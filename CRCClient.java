import java.util.*;
import java.io.*;
import java.net.*;

class CRCClient{
	public static void main(String arg[])throws UnknownHostException,IOException,NoSuchElementException{
		Scanner sc = new Scanner(System.in);
		Socket s = new Socket("127.0.0.1",1234);
		System.out.println("Connected");
		System.out.println();
		Scanner sc1 = new Scanner(s.getInputStream());
		Random ran = new Random();
	for(int o=0;o<10;o++){
		System.out.println("----------------------------------");
//		System.out.println("Enter Size of Dataword:");
	//	int si_data = sc.nextInt();
		int si_data = 100;
//		System.out.println("Enter Size of CodeWord:");
	//	int si_code = sc.nextInt();
		int si_code = 33;
		int d[] = new int [si_data+si_code-1];
		d[0] = 1;
		for(int i=1;i<si_data;i++){
			d[i] = ran.nextInt(2);
		}
		System.out.println("DataWord Generated is:");
		for(int i=0;i<si_data;i++){
			System.out.print(d[i]+" ");
		}
		System.out.println();
		for(int i=si_data;i<si_data+si_code-1;i++){
			d[i] = 0;
		}
		System.out.println();
		System.out.println("Dataword After Adding Redundancy bit Is:");
		for(int i=0;i<si_data+si_code-1;i++){
			System.out.print(d[i]+" ");
		}
		System.out.println();

		int c[] = new int [si_code];
		c[0] = 1;
		for(int i=1;i<si_code;i++){
			c[i] = ran.nextInt(2);
		}
		System.out.println();
		System.out.println("Code Word Is:");
		for(int i =0;i<si_code;i++){
			System.out.print(c[i]+" ");
		}
		System.out.println();
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
		while(j<si_code && equal!=si_code){
			for(m=(0+j);m<(si_code+j);m++){
				temp[m] = (temp1[m] + c[m-j]) % 2;
//					System.out.print(temp[m]+" ");
			}
//					System.out.println();
			equal=0;
			j =0;
			count = 0;
			while(temp[j]==0){
				j++;
				count++;
			}
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
		System.out.println();
		System.out.println("The Final Dataword:");
		for(int i=si_data+si_code-2;i>si_data-1;i--){
			d[i] = temp[i];
		}
		int num=0;
		String Stringdata="";
		String gen="";
		for(int i=0;i<si_data+si_code-1;i++){
			System.out.print(d[i]+" ");
			Stringdata=Stringdata+d[i];
//			PrintStream p = new PrintStream(s.getOutputStream());
//			p.println(d[i]);
			if(num<si_code){
				//p.println(c[i]);
				gen = gen+c[i];
				num++;
			}
			//p.println(c[i]);
		//	String temp3 = sc1.next();
		//	System.out.println(temp3);
		}
			PrintStream p = new PrintStream(s.getOutputStream());
			p.println(Stringdata);
			p.println(gen);
			//String temp3 = sc1.next();
			//System.out.println(temp3);
			System.out.println();
			String st = sc1.next();
			System.out.println();
			System.out.println(st);

//		for(int i=0;i<si_code-1;i++){
//			PrintStream p = new PrintStream(s.getOutputStream());
//			p.println(c[i]);	
//		}
		System.out.println();
	}
		String pak = sc1.next();
		System.out.println("Total Packet Corrupted:"+pak);
}
}
