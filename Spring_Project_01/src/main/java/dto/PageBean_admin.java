package dto;

public class PageBean_admin {

	private int min; // 최소 페이지 번호

	private int max; // 최대 페이지 번호

	private int prevPage; // 이전 버튼의 페이지 번호

	private int nextPage; // 다음 버튼의 페이지 번호

	private int pageCnt; // 전체 페이지 개수

	private int currentPage; // 현재 페이지 번호

	public PageBean_admin(int contentCnt, // 전체 글 개수
			int currentPage, // 현재 페이지 번호 (멤버 변수)
			int contentPageCnt, // 페이지당 글의 개수 (= 10개)
			int paginationCnt // 페이지 버튼의 개수
	) {
		this.currentPage = currentPage; // 현재 페이지 번호

		this.pageCnt = contentCnt / contentPageCnt; // 전체 페이지 개수
		if (contentCnt % contentPageCnt > 0) {
			pageCnt++; // 마지막 페이지 추가
		} // if

		/*
		 * 최소 시작번호 = 1 최소 시작번호 = 11 최소 시작번호 = 21 currentPage
		 */

		/*
		 * 그러나 데이터 베이스는 0부터 시작 1 - 1 = 0 11 - 1 = 10 21 - 1 = 20 (currentPage - 1)
		 */

		/*
		 * 페이지 당 글의 개수로 나눔 0 / 10 = 0 10 / 10 = 1 20 / 10 = 2 (currentPage - 1) /
		 * contentPageCnt
		 * 
		 * 페이지 당 글의 개수로 다시 곱함 0 * 10 = 0 1 * 10 = 10 2 * 10 = 20 ((currentPage - 1) /
		 * contentPageCnt) * contentPageCnt
		 * 
		 * 1을 더해줌 0 + 1 = 1 10 + 1 = 11 20 + 1 = 21 ((currentPage - 1) / contentPageCnt)
		 * * contentPageCnt + 1
		 */
		this.min = ((currentPage - 1) / contentPageCnt) * contentPageCnt + 1;
		this.max = min + paginationCnt - 1;

		if (max > pageCnt) {
			this.max = pageCnt;
		} // if

		this.prevPage = min - 1; // 이전 버튼의 페이지 번호
		this.nextPage = max + 1; // 다음 버튼의 페이지 번호
		if (nextPage > pageCnt) {
			this.nextPage = pageCnt;
		} // if
	} // constructor

	public int getMin() {
		return min;
	}
	public int getMax() {
		return max;
	}
	public int getPrevPage() {
		return prevPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public int getPageCnt() {
		return pageCnt;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	
}
