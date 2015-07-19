
public class Percolation {
	
	private int[] blockOpen;
	
	private WeightedQuickUnionUF initGrid;
	
	private int N;
	
	public Percolation(int N) // create N by N grid, with all sites blocked
	{
		if(N <= 0)
		{
			throw new IllegalArgumentException();
		}
		
		initGrid = new WeightedQuickUnionUF(N*N+2);//0 would be the virtual top site and N+1 would be the virtual bottom site
		
		this.N = N;
		
		//Blocking all sites. If 0 then site is blocked if 1 it is open.
		blockOpen = new int[N*N+2];
		
	}
	public void open(int i, int j) // open site (row i, column j) if it is not open already
	{
		if(i < 1 || i > blockOpen.length-2 || j < 1 || j > blockOpen.length-2 )
		{
			throw new IndexOutOfBoundsException();
		}
		
		int blkNumber = (i-1)*N + j; //Getting the corresponding array cell
		
		blockOpen[blkNumber] = 1;
			
			if(i == 1)
			{
				if(j != 1)
				{
					if(blockOpen[blkNumber-1] == 1)
					{
						initGrid.union(blkNumber-1, blkNumber); //Connecting the left cell
					}
				}
				
				if(j != N)
				{
					if(blockOpen[blkNumber+1] == 1)
					{
						initGrid.union(blkNumber+1, blkNumber); //Connecting the right cell
					}
				}
				
				int botBlkNumber = (i)*N +j;
				
				if(blockOpen[botBlkNumber] == 1)
				{
					initGrid.union(botBlkNumber, blkNumber); //Connecting the bottom cell
				}
				
				initGrid.union(0, blkNumber);//Connecting to virtual top
				
			}
			else if(i == N)
			{
				if(j != 0)
				{
					if(blockOpen[blkNumber-1] == 1)
					{
						initGrid.union(blkNumber-1, blkNumber); //Connecting the left cell
					}
				}
				
				if(j != N)
				{
					if(blockOpen[blkNumber+1] == 1)
					{
						initGrid.union(blkNumber+1, blkNumber); //Connecting the right cell
					}
				}
				
				int topBlkNumber = (i-2)*N +j;
				
				if(blockOpen[topBlkNumber] == 1)
				{
					initGrid.union(topBlkNumber, blkNumber); //Connecting the top cell
				}
				
				initGrid.union(blockOpen.length-1, blkNumber);//Connecting to virtual bottom
			}
			else
			{
				if(j != N)
				{
					if(blockOpen[blkNumber+1] == 1)
					{
						initGrid.union(blkNumber+1, blkNumber); //Connecting the right cell
					}
				}
				
				if(j != 1)
				{
					if(blockOpen[blkNumber-1] == 1)
					{
						initGrid.union(blkNumber-1, blkNumber); //Connecting the left cell
					}
				}
				
				int botBlkNumber = (i)*N +j;
				
				if(blockOpen[botBlkNumber] == 1)
				{
					initGrid.union(botBlkNumber, blkNumber); //Connecting the bottom cell
				}
				
				int topBlkNumber = (i-2)*N +j;
				
				if(blockOpen[topBlkNumber] == 1)
				{
					initGrid.union(topBlkNumber, blkNumber); //Connecting the top cell
				}
			}
			
			
	}
	
	
	public boolean isOpen(int i, int j) // is site (row i, column j) open?
	{
		if(i < 1 || i > blockOpen.length-2 || j < 1 || j > blockOpen.length-2 )
		{
			throw new IndexOutOfBoundsException();
		}
		
		int blkNumber = (i-1)*N + j;
		
		if(blockOpen[blkNumber] == 1)//If it is one then the site is open
		{
			return true;
		}
		
		return false;
	}
	
	
	public boolean isFull(int i, int j) // is site (row i, column j) full?
	{
		if(i < 1 || i > blockOpen.length-2 || j < 1 || j > blockOpen.length-2 )
		{
			throw new IndexOutOfBoundsException();
		}
		
		int blkNumber = (i-1)*N + j;
		
		if(initGrid.connected(blkNumber, 0))//If virtual top is connected to this node then return true
		{
			return true;
		}
		
		return false;
	}
	
	
	public boolean percolates() // does the system percolate?
	{
		
		if(initGrid.connected(0, blockOpen.length-1)) //If virtual top and virtual bottom are connected then return true
		{
			return true;
		}
		
		return false;
	}

}
