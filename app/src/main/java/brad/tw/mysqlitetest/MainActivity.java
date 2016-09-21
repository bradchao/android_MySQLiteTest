package brad.tw.mysqlitetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private MyDBHelper dbhelper;
    private SQLiteDatabase db;

    private TextView mesg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mesg = (TextView)findViewById(R.id.mesg);

        dbhelper = new MyDBHelper(this,"brad", null, 1);
        db = dbhelper.getReadableDatabase();

    }
    public void insert(View v){
        // INSERT INTO cust (cname,tel,birthday) VALUES ('brad','123','1999-01-02');
        ContentValues data = new ContentValues();
        data.put("cname", "gary");
        data.put("tel", "321");
        data.put("birthday","1999-02-12");
        db.insert("cust",null,data);
        query(null);
    }
    public void delete(View v){
        // DELETE FROM cust WHERE id = 3 and cname = 'brad';
        db.delete("cust", "id = ? and cname = ?", new String[]{"3","brad"});
        query(null);
    }
    public void update(View v){

    }
    public void query(View v){
        mesg.setText("");
        Cursor c = db.query("cust",null,null,null,null,null,null);
        while (c.moveToNext()){
            String id = c.getString(c.getColumnIndex("id"));
            String cname = c.getString(c.getColumnIndex("cname"));
            String tel = c.getString(c.getColumnIndex("tel"));
            String birthday = c.getString(c.getColumnIndex("birthday"));
            mesg.append(id+":"+cname+":"+tel+":"+birthday+"\n");
        }
    }

}
