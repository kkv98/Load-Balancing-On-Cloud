import java.io.*;
import java.util.*;
import java.util.concurrent.*;

class Final_sim
{
public static void main(String args[])
{
Scanner in=new Scanner(System.in);
System.out.println("Simulation Started");
Create c=new Create();
c.basic();
c.printbasics();
int pause=in.nextInt();
InsertVm ivm=new InsertVm();
ivm.createVm();
c.printbasics();
pause=in.nextInt();
System.out.println("Do you want to create a VM: 1.Yes 2.No");
int ch1=in.nextInt();
if(ch1==1)
{
System.out.println("index,id,max_storage,max_ram,max_mips,used storage,used ram,used mips:");
String ind=in.next();
String id=in.next();
String storage=in.next();
String ram=in.next();
String mips=in.next();
String h_id=null;
String usedst=in.next();
float freest=Float.parseFloat(storage)-Float.parseFloat(usedst);
String frees=String.valueOf(freest);
String usedr=in.next();
float freer=Float.parseFloat(ram)-Float.parseFloat(usedr);
String freerm=String.valueOf(freer);
String usedmp=in.next();
int freemp=Integer.parseInt(mips)-Integer.parseInt(usedmp);
String freem=String.valueOf(freemp);
c.dots("Searching appropriate Host");
System.out.println("Found Suitable Host");
c.dots("Connecting");
c.delay(1);
ivm.insert(ind,storage,ram,mips,id,h_id,usedr,freerm,usedst,frees,usedmp,freem);
c.printbasics();
pause=in.nextInt();
}
LiveVm lv=new LiveVm();
for(int y=0;y<3;y++)
{
lv.job_Shd();
c.delay(3);
System.out.println("\n");
System.out.println("Job Scheduling...");
System.out.println("Round Robin Checking of hosts...Iteration: "+(y+1));
c.printbasics();
lv.overload();
}
c.delay(3);
System.out.println("Simulation Ended");
}
}

class Create 
{
int i,j,k,l,cld_size;
int net_size[]=new int[3];
int datc_size[][]=new int[3][3];
String line;

static List <Cloud> C_list=new ArrayList<Cloud>();
static List <Network> N_list=new ArrayList<Network>();
static List <Datacenter> D_list=new ArrayList<Datacenter>();
static List <Host> H_list=new ArrayList<Host>();
static List <Vm> V_list=new ArrayList<Vm>();

void basic()
{
int p=0,q=0;
String[][] words=getSplit("test.txt"); 
cld_size=Integer.parseInt(words[p][q++]);
for(i=0;i<cld_size;i++)
{
	net_size[i]=Integer.parseInt(words[p][q++]);
	for(j=0;j<net_size[i];j++)
	{		
		datc_size[i][j]=Integer.parseInt(words[p][q++]);
	}
}
p++;q=0;
C_list.add(new Cloud(words[p][q++],words[p][q++],words[p][q++],words[p][q++],words[p][q++],words[p][q++],words[p][q++],words[p][q++],words[p][q++],words[p][q++]));
for(i=0;i<cld_size;i++)
{	
	p++;q=0;
	N_list.add(new Network(words[p][q++],words[p][q++],words[p][q++],words[p][q++],words[p][q++],words[p][q++],words[p][q++],words[p][q++],words[p][q++],words[p][q++]));
	for(j=0;j<net_size[i];j++)
	{
		p++;q=0;
		D_list.add(new Datacenter(words[p][q++],words[p][q++],words[p][q++],words[p][q++],words[p][q++],words[p][q++],words[p][q++],words[p][q++],words[p][q++],words[p][q++]));
		for(k=0;k<datc_size[i][j];k++)
		{
			p++;q=0;
			H_list.add(new Host(words[p][q++],words[p][q++],words[p][q++],words[p][q++],words[p][q++],words[p][q++],words[p][q++],words[p][q++],words[p][q++],words[p][q++],words[p][q++]));
		}
	}
}
}

void printbasics()
{
delay(2);
int flag1=0,flag2=0,flag3=0,flag4=0;
System.out.println("CLOUD "+C_list.get(0));
for(int i=0;i<cld_size;i++)
{
	System.out.println("Network "+(i+1)+": "+N_list.get(flag1++));
	for(int j=0;j<net_size[i];j++)
	{
		System.out.println("\t\t\t DataCenter "+(j+1)+": "+D_list.get(flag2++));
		for(int k=0;k<datc_size[i][j];k++)
		{	
			delay(1);
			System.out.println("\t\t\t\t\t Host "+(k+1)+": "+H_list.get(flag3));
			String h_id=H_list.get(flag3).get_host_id();
			for(int l=0;l<V_list.size();l++)
			{
				if((V_list.get(l).get_host_id()).equals(h_id))
				{
					System.out.println("\t\t\t\t\t\t\t VM "+V_list.get(l));
				}
			}
			flag3++;
		}
	}
}
}

String[][] getSplit(String filename) 
{
int i=0;
String line;
String[][] fin=new String[26][7];
try
{
	BufferedReader br;
	br=new BufferedReader(new FileReader(filename));
	while((line=br.readLine())!=null)
	{
		String[] words=line.split(" ");
		fin[i++]=words;
	}
	return fin;
}
catch(Exception e)
{
	System.out.println(e);
	return null;
}
}

void delay(int time)
{
	try
	{
		TimeUnit.SECONDS.sleep(time);
	}
	catch(InterruptedException ex)
	{
		System.out.println(ex);
	}
}

void dots(String txt)
{
for(int i=1;i<=9;i++)
{
	delay(1);
	if(j==0)
	{
		System.out.print("\r"+txt+"."+'\0'+'\0');
		j++;
	}
	else if(j==1)
	{	
		System.out.print("\r"+txt+"..");
		j++;
	}
	else if(j==2)
	{
		System.out.print("\r"+txt+"...");
		j=0;
	}
}
System.out.println();
}

void cpy_files()
{
for(int i=1;i<=20;i++)
{
	delay(1);
	System.out.print("Copying files "+i+" of 20\r");
}
System.out.println();
}
}

class Cloud
{
float cld_ram,cld_storage,cld_usedr,cld_freer,cld_usedst,cld_freest;
String cld_id;
int cld_mips,cld_umips,cld_fmips;
public Cloud(String storage,String ram,String id,String usedst,String freest,String usedr,String freer,String mips,String umips,String fmips)
{
	cld_ram=Float.parseFloat(ram);
	cld_storage=Float.parseFloat(storage);
	cld_id=id;
	cld_usedr=Float.parseFloat(usedr);
	cld_freer=Float.parseFloat(freer);
	cld_usedst=Float.parseFloat(usedst);
	cld_freest=Float.parseFloat(freest);
	cld_mips=Integer.parseInt(mips);
	cld_umips=Integer.parseInt(umips);
	cld_fmips=Integer.parseInt(fmips);
}
@Override
public String toString()
{
return (cld_id+"\t"+cld_storage+"TB\t"+cld_ram+"GB\t"+cld_mips+"mips\t"+cld_freest+"|"+cld_usedst+"\t"+cld_freer+"|"+cld_usedr+"\t"+cld_fmips+"|"+cld_umips);
}

public void occ_cld_ram(float r)
{
this.cld_usedr=this.cld_usedr+r;
this.cld_freer=this.cld_freer-r;
}

public void occ_cld_storage(float s)
{
this.cld_usedst=this.cld_usedst+s;
this.cld_freest=this.cld_freest-s;
}

public void occ_cld_mips(int m)
{
this.cld_umips=this.cld_umips+m;
this.cld_fmips=this.cld_fmips-m;
}
}

class Network
{
float net_ram,net_storage,net_usedr,net_freer,net_usedst,net_freest;
String net_id;
int net_mips,net_umips,net_fmips;
public Network(String storage,String ram,String id,String usedst,String freest,String usedr,String freer,String mips,String umips,String fmips)
{
	net_ram=Float.parseFloat(ram);
	net_storage=Float.parseFloat(storage);
	net_id=id;
	net_usedr=Float.parseFloat(usedr);
	net_freer=Float.parseFloat(freer);
	net_usedst=Float.parseFloat(usedst);
	net_freest=Float.parseFloat(freest);
	net_mips=Integer.parseInt(mips);
	net_umips=Integer.parseInt(umips);
	net_fmips=Integer.parseInt(fmips);
}
@Override
public String toString()
{
return (net_id+"\t"+net_storage+"TB\t"+net_ram+"GB\t"+net_mips+"mips\t"+net_freest+"|"+net_usedst+"\t"+net_freer+"|"+net_usedr+"\t"+net_fmips+"|"+net_umips);
}

public String get_net_id()
{
return this.net_id;
}

public void occ_net_ram(float r)
{
this.net_usedr=this.net_usedr+r;
this.net_freer=this.net_freer-r;
}

public void occ_net_storage(float s)
{
this.net_usedst=this.net_usedst+s;
this.net_freest=this.net_freest-s;
}

public void occ_net_mips(int m)
{
this.net_umips=this.net_umips+m;
this.net_fmips=this.net_fmips-m;
}
}

class Datacenter
{
float dc_ram,dc_storage,dc_usedr,dc_freer,dc_usedst,dc_freest;
String dc_id;
int dc_mips,dc_umips,dc_fmips;
public Datacenter(String storage,String ram,String id,String usedst,String freest,String usedr,String freer,String mips,String umips,String fmips)
{
	dc_ram=Float.parseFloat(ram);
	dc_storage=Float.parseFloat(storage);
	dc_id=id;
	dc_usedr=Float.parseFloat(usedr);
	dc_freer=Float.parseFloat(freer);
	dc_usedst=Float.parseFloat(usedst);
	dc_freest=Float.parseFloat(freest);
	dc_mips=Integer.parseInt(mips);
	dc_umips=Integer.parseInt(umips);
	dc_fmips=Integer.parseInt(fmips);
}
@Override
public String toString()
{
return (dc_id+"\t"+dc_storage+"TB\t"+dc_ram+"GB\t"+dc_mips+"mips\t"+dc_freest+"|"+dc_usedst+"\t"+dc_freer+"|"+dc_usedr+"\t"+dc_fmips+"|"+dc_umips);
}

public String get_dc_id()
{
return this.dc_id;
}

public void occ_dc_ram(float r)
{
this.dc_usedr=this.dc_usedr+r;
this.dc_freer=this.dc_freer-r;
}

public void occ_dc_storage(float s)
{
this.dc_usedst=this.dc_usedst+s;
this.dc_freest=this.dc_freest-s;
}

public void occ_dc_mips(int m)
{
this.dc_umips=this.dc_umips+m;
this.dc_fmips=this.dc_fmips-m;
}
}

class Host implements Comparable<Host>
{
float hst_ram,hst_storage,hst_usedr,hst_freer,hst_usedst,hst_freest,hst_bw;
String hst_id;
int hst_mips,hst_umips,hst_fmips;
public Host(String storage,String ram,String id,String usedst,String freest,String usedr,String freer,String mips,String umips,String fmips,String bw)
{
	hst_ram=Float.parseFloat(ram);
	hst_storage=Float.parseFloat(storage);
	hst_id=id;
	hst_usedr=Float.parseFloat(usedr);
	hst_freer=Float.parseFloat(freer);
	hst_usedst=Float.parseFloat(usedst);
	hst_freest=Float.parseFloat(freest);
	hst_mips=Integer.parseInt(mips);
	hst_umips=Integer.parseInt(umips);
	hst_fmips=Integer.parseInt(fmips);
	hst_bw=Float.parseFloat(bw);
}
@Override
public String toString()
{
return (hst_id+"\t"+hst_storage+"TB\t"+hst_ram+"GB\t"+hst_mips+"mips\t"+hst_freest+"|"+hst_usedst+"\t"+hst_freer+"|"+hst_usedr+"\t"+hst_fmips+"|"+hst_umips+"\t"+hst_bw+"mbps");
}


public float get_host_storage()
{
return this.hst_storage;
}

public float get_host_ram()
{
return this.hst_ram;
}

public int get_host_mips()
{
return this.hst_mips;
}

public float get_host_fstorage()
{
return this.hst_freest;
}

public float get_host_fram()
{
return this.hst_freer;
}

public int get_host_fmips()
{
return this.hst_fmips;
}

public String get_host_id()
{
return this.hst_id;
}

public void occ_host_ram(float r)
{
this.hst_usedr=this.hst_usedr+r;
this.hst_freer=this.hst_freer-r;
}

public void occ_host_storage(float s)
{
this.hst_usedst=this.hst_usedst+s;
this.hst_freest=this.hst_freest-s;
}

public void occ_host_mips(int m)
{
this.hst_umips=this.hst_umips+m;
this.hst_fmips=this.hst_fmips-m;
}

public float get_bw()
{
return this.hst_bw;
}

@Override
public int compareTo(Host arg0)
{
float j=((Host) arg0).get_bw();
return Math.round(this.hst_bw-j);
}

}

class Vm implements Comparable<Vm>
{
int index,vm_mips,vm_umips,vm_fmips;
float vm_ram,vm_storage,vm_usedr,vm_freer,vm_usedst,vm_freest;
String vm_id,host_id;
public Vm(String i,String storage,String ram,String mips,String id,String h_id,String usedr,String freer,String usedst,String freest,String usedmp,String freemp)
{
	index=Integer.parseInt(i);
	vm_ram=Float.parseFloat(ram);
	vm_storage=Float.parseFloat(storage);
	vm_id=id;
	host_id=h_id;
	vm_mips=Integer.parseInt(mips);
	vm_umips=Integer.parseInt(usedmp);
	vm_fmips=Integer.parseInt(freemp);
	vm_usedr=Float.parseFloat(usedr);
	vm_freer=Float.parseFloat(freer);
	vm_usedst=Float.parseFloat(usedst);
	vm_freest=Float.parseFloat(freest);
}

@Override
public String toString()
{
return (index+"\t"+vm_id+"\t"+host_id+"\t"+vm_storage+"TB\t"+vm_ram+"GB\t"+vm_mips+"\t"+vm_freest+"|"+vm_usedst+"\t"+vm_freer+"|"+vm_usedr+"\t"+vm_fmips+"|"+vm_umips);
}

public int get_v_ind()
{
return this.index;
}

public String get_host_id()
{
return this.host_id;
}

public float get_v_ram()
{
return this.vm_ram;
}

public float get_v_storage()
{
return this.vm_storage;
}

public void set_v_ram(float r)
{
this.vm_ram=r;
}

public void set_v_storage(float s)
{
this.vm_storage=s;
}

public void set_v_mips(int m)
{
this.vm_mips=m;
}

public String get_vm_id()
{
return this.vm_id;
}

public int get_v_mips()
{
return this.vm_mips;
}

public float get_fstorage()
{
return this.vm_freest;
}

public float get_fram()
{
return this.vm_freer;
}

public int get_fmips()
{
return this.vm_fmips;
}

public float get_ustorage()
{
return this.vm_usedst;
}

public float get_uram()
{
return this.vm_usedr;
}

public int get_umips()
{
return this.vm_umips;
}

public void change_r(float r)
{
this.vm_freer=this.vm_freer-r;
this.vm_usedr=this.vm_usedr+r;
}

public void change_s(float s)
{
this.vm_freest=this.vm_freest-s;
this.vm_usedst=this.vm_usedst+s;
}

public void change_m(int m)
{
this.vm_fmips=this.vm_fmips-m;
this.vm_umips=this.vm_umips+m;
}

@Override
public int compareTo(Vm arg0)
{
float j=((Vm) arg0).get_v_ram();
return Math.round(this.vm_ram-j);
}
}

class FindVm implements Comparable<FindVm>
{
float fvm_ram,fvm_storage;
int fvm_mips;
String fhost_id;
int index;
public FindVm(int i,float storage,float ram,int mips,String id)
{
	fvm_ram=ram;
	fvm_storage=storage;
	fvm_mips=mips;
	fhost_id=id;
	index=i;
}
@Override
public String toString()
{
return (index+1+"\t"+fvm_storage+"TB\t"+fvm_ram+"GB\t"+"\t"+fvm_mips+"mips\t"+fhost_id);
}

public int get_index()
{
return this.index;
}

public static final Comparator<FindVm> pComparator=new Comparator<FindVm>()
{
@Override
public int compare(FindVm obj1,FindVm obj2)
{
if(obj1.fvm_ram==obj2.fvm_ram)
{
return Math.round(obj1.fvm_storage-obj2.fvm_storage);
}
else
{
return Math.round(obj1.fvm_ram-obj2.fvm_ram);
}
}
};

@Override
public int compareTo(FindVm arg0)
{
return 0;
}
}

class InsertVm extends Create
{
int[] find_id=new int[7];
static int index=1;
List <FindVm> FV_list=new ArrayList<FindVm>();
void createVm()
{
int p=0,q=0;
String[][] vms=getSplit("vms.txt"); 
int vm_size=Integer.parseInt(vms[p][q]);
for(int i=0;i<vm_size;i++)
{
p++;q=0;
insert(vms[p][q++],vms[p][q++],vms[p][q++],vms[p][q++],vms[p][q++],vms[p][q++],vms[p][q++],vms[p][q++],vms[p][q++],vms[p][q++],vms[p][q++],vms[p][q++]);
//printFVm();
}
}

void insert(String ind,String storage,String ram,String mips,String id,String h_id,String usedr,String freer,String usedst,String freest,String usedmp,String freemp)
{
int j=0;
float r=Float.parseFloat(ram);
float s=Float.parseFloat(storage);
int m=Integer.parseInt(mips);
float x1,x2;
int x3;
for(int i=0;i<H_list.size();i++)
{
	if((x1=H_list.get(i).get_host_fram())>=r&&(x2=H_list.get(i).get_host_fstorage())>=s&&(x3=H_list.get(i).get_host_fmips())>=m)
	{
		find_id[j]=i;
		String hf_id=H_list.get(i).get_host_id();
		FV_list.add(new FindVm(i,x2,x1,x3,hf_id));
		j++;
	}
}
if(FV_list.size()>1)
{
Collections.sort(FV_list,FindVm.pComparator);
}
System.out.println("Inserting VM Storage:"+s+"TB\tVM Ram:"+r+"GB\tMips:"+m);
int found=FV_list.get(0).get_index();
H_list.get(found).occ_host_ram(r);
H_list.get(found).occ_host_storage(s);
H_list.get(found).occ_host_mips(m);
h_id=H_list.get(found).get_host_id();
delay(1);
System.out.println("VM successfully inserted in Host "+h_id);
V_list.add(new Vm(ind,storage,ram,mips,id,h_id,usedr,freer,usedst,freest,usedmp,freemp));
change(h_id,r,s,m,0);
FV_list.clear();
}

void printFVm()
{
System.out.println("\n");
for(int k=0;k<FV_list.size();k++)
{
	System.out.println(FV_list.get(k));
}
}

void printVm(List<Vm> list)
{
for(int i=0;i<list.size();i++)
{
	System.out.println(list.get(i));
}
}

void printHost(List<Host> list)
{
for(int i=0;i<list.size();i++)
{
	System.out.println(list.get(i));
}
}

void change(String h_id,float r,float s,int m,int flag)
{
String n_id=h_id.substring(0,1);
String dc_id=h_id.substring(0,3);
String y;
if(flag==1)
{
for(int i=0;i<H_list.size();i++)
{
	if((y=H_list.get(i).get_host_id()).equals(h_id))
	{
		H_list.get(i).occ_host_ram(r);
		H_list.get(i).occ_host_storage(s);
		H_list.get(i).occ_host_mips(m);
		break;
	}
}
}
for(int i=0;i<N_list.size();i++)
{
	if((y=N_list.get(i).get_net_id()).equals(n_id))
	{
		N_list.get(i).occ_net_ram(r);
		N_list.get(i).occ_net_storage(s);
		N_list.get(i).occ_net_mips(m);
		break;
	}
}
for(int i=0;i<D_list.size();i++)
{
	if((y=D_list.get(i).get_dc_id()).equals(dc_id))
	{
		D_list.get(i).occ_dc_ram(r);
		D_list.get(i).occ_dc_storage(s);
		D_list.get(i).occ_dc_mips(m);
		break;
	}
}
C_list.get(i).occ_cld_ram(r);
C_list.get(i).occ_cld_storage(s);
C_list.get(i).occ_cld_mips(m);
}
}

class LiveVm extends InsertVm
{
int j,k,vf;
List <Vm> LV_list=new ArrayList<Vm>();
List <Host> Find_H_list=new ArrayList<Host>();
Scanner in1=new Scanner(System.in);
InsertVm iv=new InsertVm();
void job_Shd()
{
for(int i=0;i<V_list.size();i++)
{
float r=0,s=0;
int m=0;
String h_id=V_list.get(i).get_host_id();
Float v_ram=V_list.get(i).get_fram();
Float v_st=V_list.get(i).get_fstorage();
int v_mp=V_list.get(i).get_fmips();
	if(v_ram!=0)
	{
		V_list.get(i).change_r(1);
		r=1;
	}
	if(v_st!=0)
	{
		V_list.get(i).change_s(1);
		s=1;
	}
	if(v_mp!=0)
	{
		V_list.get(i).change_m(10);
		m=10;
	}
}
}

void overload()
{
String ol,y;
Float olram,olst;
int olmips,flagq=0;
for(int i=0;i<H_list.size();i++)
{
	String host_id=H_list.get(i).get_host_id();
	float h_ram=(float)((0.80)*(H_list.get(i).get_host_ram()));
	float h_st=(float)((0.90)*(H_list.get(i).get_host_storage()));
	float h_mips=(float)((0.75)*(H_list.get(i).get_host_mips()));
	int h_m=(int)h_mips;
	olram=0.0f;olst=0.0f;olmips=0;
	for(int j=0;j<V_list.size();j++)
	{
		if((ol=V_list.get(j).get_host_id()).equals(host_id))
		{
			olram+=V_list.get(j).get_uram();
			olst+=V_list.get(j).get_ustorage();
			olmips+=V_list.get(j).get_umips();
		}
	}
	if(olram>=h_ram)
	{
		System.out.println(host_id+" is Overloading in Ram...");
	}
	if(olst>=h_st)
	{
		System.out.println(host_id+" is Overloading in Storage...");	
	}
	if(olmips>=h_m)
	{
		System.out.println(host_id+" is Overloading in Processing Element...");	
	}
	if(olram>=h_ram|| olst>=h_st || olmips>=h_m)
	{
		liveMigrate(host_id);
	}
}
}

void liveMigrate(String h_id)
{
String q;
for(int i=0;i<V_list.size();i++)
{
	if((q=V_list.get(i).get_host_id()).equals(h_id))
	{
		LV_list.add(V_list.get(i));
	}
}
Collections.sort(LV_list);
String m_Vm_id=LV_list.get(0).get_vm_id();
//iv.printVm(LV_list);
dots("Checking for the Victim VM");
delay(2);
System.out.println("Victim VM Found");
delay(1);
System.out.println("Vm to be Migrated");
delay(1);
System.out.println(LV_list.get(0));
//Deletion in V_list
int fnd;
for(int i=0;i<V_list.size();i++)
{
	if((q=V_list.get(i).get_vm_id()).equals(m_Vm_id))
	{
		fnd=i;
		V_list.remove(i);
	}
}
//System.out.println(V_list);
//Getting appropriate Vm details
int ind=LV_list.get(0).get_v_ind();
float storage=LV_list.get(0).get_v_storage();
float ram=LV_list.get(0).get_v_ram();
int mips=LV_list.get(0).get_v_mips();
String id=LV_list.get(0).get_vm_id();
String hid=LV_list.get(0).get_host_id();
float usedr=LV_list.get(0).get_uram();
float freer=LV_list.get(0).get_fram();
float usedst=LV_list.get(0).get_ustorage();
float freest=LV_list.get(0).get_fstorage();
int usedmp=LV_list.get(0).get_umips();
int freemp=LV_list.get(0).get_fmips();

iv.change(hid,-ram,-storage,-mips,1);
//Finding suitable set of Hosts
float h_r,h_s;
int h_m;
for(int i=0;i<H_list.size();i++)
{
	if(((h_r=H_list.get(i).get_host_ram())>ram)&&((h_s=H_list.get(i).get_host_storage())>storage)&&((h_m=H_list.get(i).get_host_mips())>mips))
	{
		Find_H_list.add(H_list.get(i));
	}
}
dots("Searching for the appropriate host");
delay(2);

//Sorting with respect to bandwidth
Collections.sort(Find_H_list);
Collections.reverse(Find_H_list);
//System.out.println(Find_H_list);
String hd=Find_H_list.get(0).get_host_id();
int found=-1;
for(int i=0;i<H_list.size();i++)
{
	if((q=H_list.get(i).get_host_id()).equals(hd))
	{
		found=i;
		break;
	}
}
H_list.get(found).occ_host_ram(ram);
H_list.get(found).occ_host_storage(storage);
hd=H_list.get(found).get_host_id();
System.out.println("Found Suitable host(Pre Migration) :"+hd);
delay(2);
dots("Reserving the required Ram,Storage,Mips");
delay(2);
V_list.add(new Vm(ind+"",storage+"",ram+"",mips+"",id,hd,usedr+"",freer+"",usedst+"",freest+"",usedmp+"",freemp+""));
iv.change(hd,ram,storage,mips,0);
LV_list.clear();
Find_H_list.clear();
dots("Iterative pre-copy of pages about to start");
delay(2);
cpy_files();
delay(2);
dots("Identifying and Copying Dirty pages");
delay(2);
dots("Stop and Copy process");
delay(3);
System.out.println("VM successfully migrated to another host");
delay(2);
System.out.println("Migrated VM is under Activation");
}
}