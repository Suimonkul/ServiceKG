//package appkg.kg.servicekg.database;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.provider.BaseColumns;
//
//import java.util.ArrayList;
//
//import appkg.kg.servicekg.model.Info;
//
///**
// * Created by Suimonkul on 04.06.2016.
// */
//public class DataHelper extends SQLiteOpenHelper implements BaseColumns {
//
//    private static final String DB_NAME = "servicekg.db";
//    private static final String TABLE_NAME = "favorite";
//    private static final int DB_VERSION = 1;
//
//    public static final String ADVERT_ID = "product_id";
//    public static final String TITLE = "title";
//    public static final String SHOP = "shop";
//    public static final String DESC = "desc";
//    public static final String PRODUCT_URL = "product_url";
//    public static final String IMAGE_URL = "image_url";
//    public static final String PRICE_SOM = "price_som";
//    public static final String PRICE_USD = "price_usd";
//    public static final String PHONE = "phone";
//
//    public DataHelper(Context context) {
//        super(context, DB_NAME, null, DB_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        String CREATE_SCRIPT = "create table " + TABLE_NAME + " (" +
//                BaseColumns._ID + " integer primary key autoincrement, " +
//                ADVERT_ID + " text, " +
//                TITLE + " text, " +
//                SHOP + " text, " +
//                DESC + " text, " +
//                PRODUCT_URL + " text, " +
//                IMAGE_URL + " text, " +
//                PRICE_SOM + " text, " +
//                PRICE_USD + " text, " +
//                PHONE + " text);";
//        db.execSQL(CREATE_SCRIPT);
//
//
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//    }
//
//
//    public void removeProduct(int productId) {
//        getWritableDatabase().delete(TABLE_NAME, ADVERT_ID + " = " + productId, null);
//    }
//
//    public void saveProduct(Info info) {
//        ContentValues values = new ContentValues();
//        values.put(ADVERT_ID, info.getId());
//        values.put(TITLE, info.getName());
//        values.put(DESC, info.getDescription());
//        values.put(PHONE, info.getPhone());
//        getWritableDatabase().insert(TABLE_NAME, null, values);
//    }
//
//
//    public ArrayList<Info> getProducts() {
//        ArrayList<Info> list = new ArrayList<>();
//        try {
//            if (getWritableDatabase() == null) return list;
//            Cursor cursor = getReadableDatabase().query(TABLE_NAME,
//                    null, null, null,
//                    null, null, BaseColumns._ID + " DESC");
//            if (cursor != null && cursor.getCount() > 0) {
//                while (cursor.moveToNext()) {
//                    Info info = new Info(cursor.getString(cursor.getColumnIndex(ADVERT_ID)));
//                    info.setId(cursor.getString(cursor.getColumnIndex(BaseColumns._ID)));
//                    info.setName(cursor.getString(cursor.getColumnIndex(TITLE)));
//                    info.setDescription(cursor.getString(cursor.getColumnIndex(DESC)));
//                    info.setPhone(cursor.getString(cursor.getColumnIndex(PHONE)));
//                    list.add(info);
//                }
//            }
//            if (cursor != null) cursor.close();
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    public boolean isExist(int adverId) {
//        boolean isExist = false;
//        String selection = ADVERT_ID + " = " + adverId;
//        Cursor cursor = getReadableDatabase().query(TABLE_NAME, null, selection, null, null, null, null);
//        if (cursor != null) {
//            if (cursor.getCount() > 0) isExist = true;
//            cursor.close();
//        }
//        return isExist;
//    }
//
//
//}
