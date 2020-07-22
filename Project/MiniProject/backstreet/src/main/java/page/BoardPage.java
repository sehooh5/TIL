package page;

public class BoardPage {

	private int listSize = 10;                
	//0316 jung 초기값으로 목록개수를 10으로 셋팅
	private int rangeSize = 10;            
	//0316 jung 초기값으로 페이지범위를 10으로 셋팅
	private int page;
	//0316 현재 페이지 정보
	private int range;
	//현재 페이지 범위 정보
	private int listCnt;
	//게시물의 총 개수
	private int pageCnt;
	private int startPage;
	private int startList;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	public int getRangeSize() {
		return rangeSize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getListSize() {
		return listSize;
	}

	public void setListSize(int listSize) {
		this.listSize = listSize;
	}
	
	public int getListCnt() {
		return listCnt;
	}

	public void setListCnt(int listCnt) {
		this.listCnt = listCnt;
	}
	
	public int getStartList() {
		return startList;
	}

	public void pageInfo(int page, int range, int listCnt) {
		//0316 jung page : 현재 페이지 정보, range : 현재 페이지 범위 정보, listCnt: 게시물의 총갯수 
		this.page = page;
		this.range = range;
		this.listCnt = listCnt;
		
		//0316 jung 전체 페이지수 
		//전체 게시물 개수(listCnt)와 기본값으로 설정한 한 페이지당 목록개수(listSize)를 사용해 전체 페이지 개수를 구한다.
		this.pageCnt = (int) Math.ceil(listCnt/listSize);
		
		//0316 jung 시작 페이지
		this.startPage = (range - 1) * rangeSize + 1 ;
		
		//0316 jung 끝 페이지
		//다음에 나올 [다음]버튼의 활성화 여부를 판단하기 위해 작성, 각페이지 범위의 마지막 번호 구하는 방법
		this.endPage = range * rangeSize;
				
		//0316 jung 게시판 시작번호 : 각 페이지 범위의 시작번호를 구하는 방법
		this.startList = (page - 1) * listSize;
		
		//0316 jung 이전 버튼 상태
		this.prev = range == 1 ? false : true;
		
		//0316 jung 다음 버튼 상태
		// 마지막에 있는 if 구문은 마지막 번호와 페이지의 총 갯수를 비교하여 마지막 번호가 총 갯수보다 크다면 마지막 번호로 셋팅되도록 설정
		// 또한 다음페이지가 없다면 페이지에 대한 이동도 할 수 없으므로, 다음 버튼을 비활성화 하도록 만든다.
		this.next = endPage > pageCnt ? false : true;
		if (this.endPage > this.pageCnt) {
			this.endPage = this.pageCnt;
			this.next = false;
		}
	}
}


