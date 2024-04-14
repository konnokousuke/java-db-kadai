package kadai_007;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class posts_Chapter07 {

	public static void main(String[] args) {
		
		Connection con = null;
		Statement statement = null;
		
		
		try {
			//データベースに接続
			  con = DriverManager.getConnection(
					"jdbc:mysql://localhost/challenge_java",
					"root",
					"fH3$ohdi"
		    );
			
			System.out.println("データベース接続成功：" + con);
			
			//投稿データ
			String insertSql = "INSERT INTO posts (user_id, posted_at, post_content, likes) VALUES"
			        + "(1003, '2023-02-08', '昨日の夜は徹夜でした・・', 13),"
					+ "(1002, '2023-02-08', 'お疲れ様です！', 12),"
					+ "(1003, '2023-02-09', '今日も頑張ります！', 18),"
					+ "(1001, '2023-02-09', '無理は禁物ですよ！', 17),"
					+ "(1002, '2023-02-10', '明日から連休ですね！', 20)";
			
			//SQLクエリを準備
			statement = con.createStatement();
			
			//SQLクエリを実行
			int rowCnt = statement.executeUpdate(insertSql);
			System.out.println("レコード追加を実行します");
			
			//実行結果を出力
			System.out.println(rowCnt + "件のレコードが追加されました");
					 
			//ユーザーIDが1002の投稿を検索して取得
			String selectSql = "SELECT posted_at, post_content, likes FROM posts WHERE user_id = 1002";
			ResultSet resultSet = statement.executeQuery(selectSql);
			System.out.println("ユーザーIDが" + 1002 + "のレコードを検索しました");
			
			//検索結果を表示
			int count = 1;
			while(resultSet.next()) {
				java.sql.Date postedAt = resultSet.getDate("posted_at");
				String postContent = resultSet.getString("post_content");
				int likes = resultSet.getInt("likes");
				System.out.println(count + "件目：投稿日時=" + postedAt + "／投稿内容=" + postContent + "／いいね数=" + likes);
				count++;
			}
			
		
		} catch(SQLException e) {
			System.out.println("エラー発生：" + e.getMessage());
		} finally {
			//使用したオブジェクトを解放
			if( statement != null ) {
				try { statement.close(); } catch(SQLException ignore) {}
			}
			if( con != null ) {
				try { con.close(); } catch(SQLException ignore) {}
			}
		}

	}

}
