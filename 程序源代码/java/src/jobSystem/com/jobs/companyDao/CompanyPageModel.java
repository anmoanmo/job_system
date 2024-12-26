package src.jobSystem.com.jobs.companyDao;

public class CompanyPageModel {
	CompanyDao companyDao = new CompanyDao();
	private String SearchTexts;      //搜索框的文字
	
	private int companyPageNo;      //第几页
	private int companyPageSize;    //一页有多少条
	
	private int companyTotalCount;  //总条数
	private int companyTotalPage;   //共有多少页
	
	public int getCompanyPageNo() {
		return companyPageNo;
	}
	public void setCompanyPageNo(int companyPageNo) {
		this.companyPageNo = companyPageNo;
	}
	public int getCompanyPageSize() {
		return companyPageSize;
	}
	public void setCompanyPageSize(int companyPageSize) {
		this.companyPageSize = companyPageSize;
	}
	public int getCompanyTotalCount() {
		return companyTotalCount;
	}
	public void setCompanyTotalCount(int companyTotalCount) {
		this.companyTotalCount = companyTotalCount;
	}
	public int getCompanyTotalPage() {
		return companyTotalPage;
	}
	public void setCompanyTotalPage(int companyTotalPage) {
		this.companyTotalPage = companyTotalPage;
	}
	public String getSearchText() {
		return SearchTexts;
	}
	
	public void setSearchText(String SearchText) {
		this.SearchTexts=SearchText;
		this.companyPageNo = 1;
		this.companyTotalCount = (int)companyDao.getPositionTotal(SearchText);
		if(companyTotalCount%companyPageSize == 0) {
			companyTotalPage = companyTotalCount/companyPageSize;
		}else {
			companyTotalPage = companyTotalCount/companyPageSize+1;
		}
		if(companyTotalCount == 0) {
			companyTotalPage = 1;
		}
	}
	
	//构造方法
	public CompanyPageModel(int companyPageSize) {
		this.companyPageNo = 1;
		this.companyPageSize = companyPageSize;
		this.companyTotalCount = (int)companyDao.getPositionTotal("");
		if(companyTotalCount%companyPageSize == 0) {
			companyTotalPage = companyTotalCount/companyPageSize;
		}else {
			companyTotalPage = companyTotalCount/companyPageSize+1;
		}
		if(companyTotalCount == 0) {
			companyTotalPage = 1;
		}
	}
	
	//下一页
	public void next() {
		if(companyPageNo == companyTotalPage) {
			
		}else {
			companyPageNo++;
		}
	}
	
	//上一页
	public void preve() {
		if(companyPageNo == 1) {
			
		}else {
			companyPageNo--;
		}
	}
	
	//首页
	public void first() {
		companyPageNo = 1;
	}
	
	//尾页
	public void last() {
		companyPageNo = companyTotalPage;
	}
	
}

