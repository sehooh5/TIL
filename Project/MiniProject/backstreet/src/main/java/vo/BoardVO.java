package vo;

public class BoardVO {
	/* jung 0309 BoardVO, getter, setter 생성 */
	private int board_id;
	private String title;
	private String content;
	private String writedate;
	private int cnt;
	private String writer;
	
	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWritedate() {
		return writedate;
	}

	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}
	@Override
	/* 0227 jung Override 생성 */
	public String toString() {
		return "아이디: "+this.board_id+",제목: "+this.title+", 내용: "+this.content+", 작성날짜: "+this.writedate+", 조회수: "+this.cnt+",작성자: "+this.writer;
	}
}
