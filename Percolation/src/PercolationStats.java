
public class PercolationStats {

	private double[] a;
	
	public PercolationStats(int N, int T) // perform T independent experiments on an N by N grid
	{
		
		if(N <= 0 || T <= 0)
		{
			throw new IllegalArgumentException();
		}
		
		a = new double[T];
		
		for(int k = 0; k < T; k++)
		{
			Percolation chkPercolation = new Percolation(N);
			
			int openSites = 0; //Number of open sites
			
			double threshold = 0;//Threshold is zero
			
			boolean percolate = false;//It does not percolate
			
			while(!percolate)
			{
				
				int i = 0;
				
				int j = 0;

				i = StdRandom.uniform(N)+1;
				
				j = StdRandom.uniform(N)+1;

				if(!chkPercolation.isOpen(i, j))
				{
					chkPercolation.open(i, j);
					
					openSites++;					
				}
				else
				{
					chkPercolation.open(i, j);
				}
				
				if(chkPercolation.percolates())
				{
					threshold = (double) openSites/(N*N);
					
					percolate = true;
				}
			}
			
			a[k] = threshold;
		}
		
	}
	
	
	public double mean() // sample mean of percolation threshold
	{
		double mean = StdStats.mean(a);
		
		return mean;
	}
	
	
	public double stddev() // sample standard deviation of percolation threshold
	{
		double stdDev = StdStats.stddev(a);
		
		return stdDev;
	}
	
	
	public double confidenceLo() // low endpoint of 95% confidence interval
	{
		double mean = StdStats.mean(a);
		
		double stdDev = StdStats.stddev(a);
		
		double confiLo = mean - (1.96*stdDev/Math.sqrt(a.length));
		
		return confiLo;
	}
	
	
	public double confidenceHi() // high endpoint of 95% confidence interval
	{
		double mean = StdStats.mean(a);
		
		double stdDev = StdStats.stddev(a);
		
		double confiHi = mean + (1.96*stdDev/Math.sqrt(a.length));
		
		return confiHi;
	}

	public static void main(String[] args) {
		
		PercolationStats stats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		
		double mean = stats.mean();
		
		double stdDev = stats.stddev();
		
		double confiLo = stats.confidenceLo();
		
		double confiHi = stats.confidenceHi();
		
		System.out.println("mean = "+mean);
		
		System.out.println("stddev = "+stdDev);
		
		System.out.println("95% confidence interval = "+confiLo+", "+confiHi);

	}

}
