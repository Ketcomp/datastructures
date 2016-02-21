import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class SetOperations {
	
	/*public static<T> T next(Iterator<T> itr){
		if(itr.hasNext()){
			return itr.next();
		}
		
		return null;
	}*/

	public static<T extends Comparable<? super T>>
    void intersect(List<T> l1, List<T> l2, List<T> outList) 
	{	if(l1.isEmpty() || l2.isEmpty()) return;
	
		Iterator<T> iter1= l1.iterator();
		Iterator<T> iter2= l2.iterator();
		T element1=iter1.next();
		T element2=iter2.next();
		while(iter1.hasNext() || iter2.hasNext())
		{
			if(element1.compareTo(element2)==-1)
			{
				if(iter1.hasNext())element1=iter1.next();
				else break;
			}
			else if(element1.compareTo(element2)==1)
			{
				if(iter2.hasNext())element2=iter2.next();
				else break;
			}
			else if(element1==element2)
			{
				outList.add(element1);
				if(iter1.hasNext())element1=iter1.next();
				if(iter2.hasNext())element2=iter2.next();
			}
		}
		if(element1==element2)
			outList.add(element1);
	}
	
	public static<T extends Comparable<? super T>>
    void union(List<T> l1, List<T> l2, List<T> outList) 
	{	
		if(l1.isEmpty()) {
			outList.addAll(l2);
			return;
		}
		
		if(l2.isEmpty()) {
			outList.addAll(l1);
			return;
		}
		
		Iterator<T> iter1= l1.iterator();
		Iterator<T> iter2= l2.iterator();
		T element1=iter1.next();
		T element2=iter2.next();
		while(iter1.hasNext() || iter2.hasNext())
		{
			if(element1.compareTo(element2)==-1)
			{
				outList.add(element1);
				//outElement= outIter.next();
				if(iter1.hasNext())element1=iter1.next();
				else break;
			}
			else if(element1.compareTo(element2)==1)
			{
				outList.add(element2);
				//outElement= outIter.next();
				if(iter2.hasNext())element2=iter2.next();
				else break;
			}
			else if(element1.compareTo(element2)==0)
			{
				outList.add(element1);
				//outElement= outIter.next();
				if(iter1.hasNext())element1=iter1.next();
				else break;
				if(iter2.hasNext())element2=iter2.next();
				else break;
			}
		}
		
		//Checking for the last remaining elements in the array.
		if(!iter1.hasNext() && !iter2.hasNext())
		{
			if(element1.compareTo(element2)==-1)
			{
				outList.add(element1);
				outList.add(element2);
			}
			else if  (element1.compareTo(element2)==1)
			{
				outList.add(element2);
				outList.add(element1);
			}
			else if (element1.compareTo(element2)==0)
			{
				outList.add(element1);
			}
		}
		else if(!iter1.hasNext())
		{
			if(element1.compareTo(element2)==0)
				element2=iter2.next();
			while(iter2.hasNext())
			{
				outList.add(element2);
				element2=iter2.next();
			}
			outList.add(element2);
		}
		else if(!iter2.hasNext())
		{
			if(element1.compareTo(element2)==0)
				element1=iter1.next();
			while(iter1.hasNext())
			{
				outList.add(element1);
				element1=iter1.next();
			}
			outList.add(element1);
		}
	}
	
	public static<T extends Comparable<? super T>>
    void difference(List<T> l1, List<T> l2, List<T> outList) 
	{	
		if(l1.isEmpty()) return;
		
		if(l2.isEmpty()) {
			outList.addAll(l1);
			return;
		}
		
		Iterator<T> iter1= l1.iterator();
		Iterator<T> iter2= l2.iterator();
		T element1=iter1.next();
		T element2=iter2.next();
		while(iter1.hasNext() || iter2.hasNext())
		{
			if(element1.compareTo(element2)==-1)
			{
				outList.add(element1);
				if(iter1.hasNext())element1=iter1.next();
				else break;
			}
			else if(element1.compareTo(element2)==1)
			{
				if(iter2.hasNext())element2=iter2.next();
				else break;
			}
			else if(element1==element2)
			{
				if(iter1.hasNext())element1=iter1.next();
				else break;
				if(iter2.hasNext())element2=iter2.next();
			}
		}
		if(!iter1.hasNext())
		{
			if(!iter2.hasNext())
			{
				if(element1.compareTo(element2)!=0)
				{
					outList.add(element1);
				}
			}
			else
			{
				if(element1.compareTo(element2)!=0)outList.add(element1);
			}
		}
    }
	public static<T> void print(List<T> list)
	{
		Iterator<T> iter= list.iterator();
		Integer I;
		while(iter.hasNext())
		{
			I= (Integer)iter.next();
			System.out.print(I+" ");
		}
		System.out.println();
	}
	
	public static void main(String args[])
	{
		LinkedList<Integer> l1= new LinkedList<Integer>();
		LinkedList<Integer> l2= new LinkedList<Integer>();
		LinkedList<Integer> outList= new LinkedList<Integer>();
		l1.add(1);
		l1.add(2);
		l1.add(6);
		l1.add(9);
		l2.add(1);
		l2.add(3);
		l2.add(6);
		l2.add(7);
		l2.add(8);
		l2.add(10);
		print(l1);
		print(l2);
		intersect(l1,l2,outList);
		//union(l1,l2,outList);
		//difference(l1,l2,outList);
		print(outList);
	}
}
