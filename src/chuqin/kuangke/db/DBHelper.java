package chuqin.kuangke.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	public static final String DB_NAME = "chuqin_kuangke"; // DB name
	private Context kkcontext;
	private DBHelper kkDbHelper;
	private SQLiteDatabase db;

	public DBHelper(Context context) {
		super(context, DB_NAME, null, 11);
		this.kkcontext = context;
	}

	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//,clothes text,doctor text,laiwang text,baby text,live text,other text,remark text
		String sql = "create table if not exists chuqin_kk(id integer primary key,time text,name text,number text,xibie text,banji text,kk_course text,kk_time text,kk_reason text)";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	public DBHelper open() {
		if (null == kkDbHelper) {
			kkDbHelper = new DBHelper(kkcontext);
		}
		db = kkDbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		db.close();
		kkDbHelper.close();
	}

	public long insert(String tableName, ContentValues values) {
		return db.insert(tableName, null, values);
	}

	public Cursor findList(String tableName, String[] columns,
			String selection, String[] selectionArgs, String groupBy,
			String having, String orderBy, String limit) {
		return db.query(tableName, columns, selection, selectionArgs, groupBy,
				having, orderBy, limit);
	}

	public Cursor exeSql(String sql) {
		return db.rawQuery(sql, null);
	}
}