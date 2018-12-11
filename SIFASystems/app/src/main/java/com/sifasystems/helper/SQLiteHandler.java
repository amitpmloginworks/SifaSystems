package com.sifasystems.helper;

import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.sifasystems.model.Center;
import com.sifasystems.model.ScanLogDetails;
import com.sifasystems.model.User;
import com.sifasystems.model.UserImage;

import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();
    private static Context context;

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_FILE_PATH="/sdcard/Sifa";

    // Database Name
    private static final String DATABASE_NAME = "SIFA_systems";

    private SQLiteDatabase database;

    // Login table name
    private static final String TABLE_USER = "user_tb";
    //Image table name
    private static final String TABLE_IMAGE = "tbl_image";
    // School table name
    private static final String TABLE_SCHOOL = "tbl_school";
     // Scan log table
    private static final String TABLE_SCAN_LOG="tbl_scan_log_activity";

    //Student Scan Details table
    private static final String STUDENT_SCAN_DETAILS="tbl_student_details";

    //Malpractice Table Details
    private static final String MALPRACTICE_DETAILS="tbl_malpractice_details";

    //Coordinator Details Table
    private static final String COORDINATOR_DETAILS="tbl_coordinator_details";

    //Attendance Details Table
    private static final String ATTENDANCE="tbl_attendance";

    //start Exam Table
    private static final String START_EXAM="tbl_start_exam";

    // Login Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_KEY = "key";
    private static final String KEY_FNAME = "fname";
    private static final String KEY_SNAME = "sname";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_LONGITUDE = "longitude";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_UPDATED_AT = "updated_at";
    private static final String KEY_TYPE = "type";


    //Image Table Columns names
    private static final String IMAGE_ID = "image_id";
    private static final String TAG_ID = "tag_id";
    private static final String EXAM_ID = "exam_id";
    private static final String STUDENT_NAME = "student_name";
    private static final String IMAGE_URL = "image_url";

    //School table column names
    private static final String CENTER_ID = "center_id";
    private static final String SCHOOL_NAME = "school_name";

    //Scan Log table column names
    private static final String SCAN_LOG_ID = "scan_log_id";
    private static final String USER_ID = "user_id";
    private static final String COORDINATOR_ID = "coordinator_id";
    private static final String DEVICE_ID = "device_id";

    //Student Details column names
    private static final String INVIGILATOR_ID = "invigilator_id";
    private static final String STUDENT_ID = "student_id";
    private static final String STUDENT_NAME_SCAN = "student_name";
    private static final String SCHOOL_NAME_SCAN = "school_name";
    private static final String PAPER = "paper";

    //Malpractice table Column names
    private static final String MALPRACTICE_INVIGILATOR_ID= "malpractice_invigilator_id";
    private static final String MALPRACTICE_STUDENT_ID="malpractice_student_id";
    private static final String MALPRACTICE_TYPE = "malpractice_type";
    private static final String MALPRACTICE_PAPER = "malpractice_paper";
    private static final String STATUS = "status";

    //Coordinator table column names
    private static final String COORDINATOR_ID_DETAILS= "coordinator_id_details";
    private static final String INVIGILATOR_NAME="invigilator_name";
    private static final String EXAM_NO = "exam_no";
    private static final String TIMESTAMP = "timestamp";

    //Attendance table column names
    private static final String ATTENDANCE_SCHOOL_NAME= "attendance_school_name";
    private static final String ATTENDANCE_CANDIDATE_NAME="attendance_candidate_name";
    private static final String ATTENDANCE_TIMESTAMP = "attendance_timestamp";

    //Start Exam column names
    private static final String EXAM_SCHOOL_NAME= "exam_school_name";
    private static final String EXAM_TIME="exam_time";
    private static final String EXAM_PAPER = "exam_paper";


    public SQLiteHandler(Context context) {
      super(context, DATABASE_NAME, null, DATABASE_VERSION);
      //  super(context, context.getExternalFilesDir(null).getAbsolutePath() + "/" + DATABASE_NAME, null, DATABASE_VERSION);
       //super(context, Environment.getExternalStorageDirectory()+ File.separator+ DATABASE_NAME, null, DATABASE_VERSION);

//        File dbfile = new File("/sdcard/sifa.db" );
//        SQLiteDatabase  db = SQLiteDatabase.openOrCreateDatabase(dbfile, null);


//        super(new ContextWrapper(context) {
//            @Override public SQLiteDatabase openOrCreateDatabase(String name,
//                                                                 int mode, SQLiteDatabase.CursorFactory factory) {
//
//                // allow database directory to be specified
//                File dir = new File("/sdcard/SIFA");
//                if(!dir.exists()) {
//                    dir.mkdirs();
//                }
//                return SQLiteDatabase.openDatabase("/sdcard/SIFA"+ "/" + DATABASE_NAME, null,
//                        SQLiteDatabase.CREATE_IF_NECESSARY);
//            }
//        }, DATABASE_NAME, null, DATABASE_VERSION);
//        this.context = context;





//        super(context, Environment.getExternalStorageDirectory()
//                + File.separator+ DATABASE_NAME, null, DATABASE_VERSION);

//        super(context, Environment.getExternalStorageDirectory()
//                + File.separator + FILE_DIR
//                + File.separator + DATABASE_NAME, null, DATABASE_VERSION);
//
//        File makeFolder = new File("/sdcard/Fekra");
//        File makeFolder1 = new File("/sdcard/Fekra/data");
//        if (!makeFolder.exists())
//            if (!makeFolder.mkdir()) {
//
//            }
//        if (!makeFolder1.exists())
//            if (!makeFolder1.mkdir()) {
//
//            }

        try {
           // database  = SQLiteDatabase.openDatabase(DATABASE_FILE_PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);
//            database=   SQLiteDatabase.openOrCreateDatabase("/sdcard/"+DATABASE_NAME,null);
//            onCreate(database);
//
//            database = SQLiteDatabase.openDatabase(DATABASE_FILE_PATH + File.separator + DATABASE_NAME, null,SQLiteDatabase.OPEN_READWRITE + SQLiteDatabase.CREATE_IF_NECESSARY);
//        v    database = SQLiteDatabase.openOrCreateDatabase(DATABASE_FILE_PATH + File.separator + DATABASE_NAME, null);
           // createTables();
            Log.d("database","Created Successfully");

        }

        catch (SQLiteException ex) {
           // onCreate(database);
        } finally {
        }


    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        //user table
        String query = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_KEY + " TEXT UNIQUE," + KEY_FNAME + " TEXT," + KEY_SNAME + " TEXT,"
                + KEY_LATITUDE + " REAL," + KEY_LONGITUDE + " REAL," + KEY_LOCATION + " TEXT," + KEY_TYPE + " INTEGER,"
                + KEY_UPDATED_AT + " TEXT" + ")";
        db.execSQL(query);


       // image table
        String query2 = "CREATE TABLE " + TABLE_IMAGE + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + IMAGE_ID + " INTEGER," + TAG_ID + " TEXT,"
                + EXAM_ID + " INTEGER," + STUDENT_NAME + " TEXT,"
                + IMAGE_URL + " TEXT" + ")";
        db.execSQL(query2);

        //School table table
        String query3 = "CREATE TABLE " + TABLE_SCHOOL + "("
                + CENTER_ID + " INTEGER PRIMARY KEY," + SCHOOL_NAME + " TEXT" + ")";
        db.execSQL(query3);

        // Scan Log Table
        String query4 = "CREATE TABLE " + TABLE_SCAN_LOG + "("
                + SCAN_LOG_ID + " INTEGER PRIMARY KEY," + USER_ID + " INTEGER," + COORDINATOR_ID + " TEXT," + DEVICE_ID + " TEXT"
                +  ")";
        db.execSQL(query4);

        // Student scan details
        String query5 = "CREATE TABLE " + STUDENT_SCAN_DETAILS + "("
                + INVIGILATOR_ID + " TEXT," + STUDENT_ID + " TEXT," + STUDENT_NAME_SCAN + " TEXT," + SCHOOL_NAME_SCAN + " TEXT,"
                + PAPER + " INTEGER" + ")";
        db.execSQL(query5);


        // Malpractice scan details
        String query6 = "CREATE TABLE " + MALPRACTICE_DETAILS + "("
                + MALPRACTICE_INVIGILATOR_ID + " TEXT," + MALPRACTICE_STUDENT_ID + " TEXT," +  MALPRACTICE_TYPE+ " TEXT," + MALPRACTICE_PAPER + " INTEGER,"
                + STATUS + " INTEGER" + ")";
        db.execSQL(query6);


        String query7 = "CREATE TABLE " + COORDINATOR_DETAILS + "("
                + COORDINATOR_ID_DETAILS + " TEXT UNIQUE," + INVIGILATOR_NAME + " TEXT," +  EXAM_NO+ " TEXT," + TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                 + ")";
        db.execSQL(query7);

        //create attendance table
        String query8 = "CREATE TABLE " + ATTENDANCE + "("
                + ATTENDANCE_SCHOOL_NAME+ " TEXT," + ATTENDANCE_CANDIDATE_NAME + " TEXT," + ATTENDANCE_TIMESTAMP + " TEXT"
                + ")";
        db.execSQL(query8);

      // create  Start Exam Table
        String query9 = "CREATE TABLE " + START_EXAM + "("
                + EXAM_SCHOOL_NAME + " TEXT," + EXAM_TIME + " TEXT," + EXAM_PAPER + " INTEGER"
                + ")";
        db.execSQL(query9);

        Log.d(TAG, "Database tables created");
    }






    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_IMAGE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCHOOL);

        // Create tables again
        onCreate(db);
    }

    /**
     * Storing user details in database
     */
    public void addUser(String key, String fname, String sname, double latitude, double longitude, String location, int type, String updated_at) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_KEY, key);
        values.put(KEY_FNAME, fname);
        values.put(KEY_SNAME, sname);
        values.put(KEY_LATITUDE, latitude);
        values.put(KEY_LONGITUDE, longitude);
        values.put(KEY_LOCATION, location);
        values.put(KEY_TYPE, type);
        values.put(KEY_UPDATED_AT, updated_at);

        // Inserting Row
        long id = db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New user inserted into sqlite: " + id);
    }

    public void addUserImage(int image_id, String tag_id, int exam_id, String student_name, String image_url) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(IMAGE_ID, image_id);
        values.put(TAG_ID, tag_id);
        values.put(EXAM_ID, exam_id);
        values.put(STUDENT_NAME, student_name);
        values.put(IMAGE_URL, image_url);

        // Inserting Row
        long id = db.insert(TABLE_IMAGE, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New Image inserted into DataBase: " + id);

    }

    public void addCenterDetails(String centerID, String schoolName){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CENTER_ID, centerID.trim());
        values.put(SCHOOL_NAME, schoolName.trim());

        // Inserting Row
        long id = db.insert(TABLE_SCHOOL, null, values);
        db.close(); // Closing database connection
        Log.d(TAG, "New centers inserted into DataBase: " + id);
    }


//	public void updateUser(User user, int send) {
//		SQLiteDatabase db = this.getWritableDatabase();
//
//		ContentValues values = new ContentValues();
//		values.put(KEY_SEND, send); // Send
//
//		// Inserting Row
//		long id = db.update(TABLE_USER, values, "name=? and phone=?", new String[] {contact.name, contact.number});
//		db.close(); // Closing database connection
//
//		Log.d(TAG, "New user inserted into sqlite: " + id);
//	}

    //Get Image data
    public ArrayList<UserImage> getImages(String tag_id) {
        ArrayList<UserImage> userImages = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER + " WHERE " + TAG_ID + "=" + tag_id;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            UserImage userImage = new UserImage();
            userImage.imageID = cursor.getInt(1);
            userImage.tagID = cursor.getString(2);
            userImage.examID = cursor.getInt(3);
            userImage.studentName = cursor.getString(4);
            userImage.imageURL = cursor.getString(5);

            userImages.add(userImage);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();

        return userImages;
    }

    /**
     * Getting user data from database
     */
    public ArrayList<User> getCoordinators(String string) {

        ArrayList<User> users = new ArrayList<User>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER + " where type=0 and (key LIKE '%" + string + "%' or fname LIKE '%" + string + "%' or sname LIKE '%" + string + "%')";


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            User user = new User();
            user.nId = cursor.getInt(0);
            user.key = cursor.getString(1);
            user.fname = cursor.getString(2);
            user.sname = cursor.getString(3);
            user.latitude = cursor.getFloat(4);
            user.longitude = cursor.getFloat(5);
            user.location = cursor.getString(6);
            user.type = cursor.getInt(7);
            user.updated_at = cursor.getString(8);

            users.add(user);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();

        return users;
    }

    public ArrayList<User> getInvigilators(String string) {

        ArrayList<User> users = new ArrayList<User>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER + " where type=1 and key LIKE '%" + string + "%'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            User user = new User();
            user.nId = cursor.getInt(0);
            user.key = cursor.getString(1);
            user.fname = cursor.getString(2);
            user.sname = cursor.getString(3);
            user.latitude = cursor.getFloat(4);
            user.longitude = cursor.getFloat(5);
            user.location = cursor.getString(6);
            user.type = cursor.getInt(7);
            user.updated_at = cursor.getString(8);
            users.add(user);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();

        return users;
    }


    public ArrayList<Center> getCenterDetails() {

        ArrayList<Center> centers = new ArrayList<Center>();
        String selectQuery = "SELECT  * FROM " + TABLE_SCHOOL ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            Center center = new Center();
            center.centerID = cursor.getString(0);
            center.schoolName = cursor.getString(1);

            centers.add(center);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return centers;
    }


    public ArrayList<User> getInvigilator(String string) {

        ArrayList<User> users = new ArrayList<User>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER + " where type=1 and key LIKE '%" + string + "%'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            User user = new User();
            user.nId = cursor.getInt(0);
            user.key = cursor.getString(1);
            user.fname = cursor.getString(2);
            user.sname = cursor.getString(3);
            user.latitude = cursor.getFloat(4);
            user.longitude = cursor.getFloat(5);
            user.location = cursor.getString(6);
            user.type = cursor.getInt(7);
            user.updated_at = cursor.getString(8);

            users.add(user);
            cursor.moveToNext();
            break;
        }
        cursor.close();
        db.close();

        return users;
    }



    /**
     * Recreate database Delete all tables and create them again
     */
    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_USER, null, null);
        db.close();

        Log.d(TAG, "Deleted all user info from sqlite");
    }


  // Store ScanLogDetails
    public void addScanLogDetails(int logId, String userId, String coordinatorId, String deviceId) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SCAN_LOG_ID, logId);
        values.put(USER_ID, userId);
        values.put(COORDINATOR_ID, coordinatorId);
        values.put(DEVICE_ID, deviceId);
        // Inserting Row
        long id = db.insert(TABLE_SCAN_LOG, null, values);
        db.close(); // Closing database connection


    }

    // Get ScanLogDetails data
    public ArrayList<ScanLogDetails> getScanLogDetails() {

        ArrayList<ScanLogDetails> scanLogDetails = new ArrayList<ScanLogDetails>();
        String selectQuery = "SELECT  * FROM " + TABLE_SCAN_LOG ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            ScanLogDetails scanLogDetail=new ScanLogDetails();
            scanLogDetail.logId = cursor.getInt(1);
            scanLogDetail.userId = cursor.getString(2);
            scanLogDetail.coordinatorId = cursor.getString(3);

            scanLogDetails.add(scanLogDetail);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return scanLogDetails;
    }

    // Add Scanned Student Details
    public void addStudentDetails(String invigilatorId, String studentId, String studentNameDetails, String schoolNameDetails, int paper) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(INVIGILATOR_ID, invigilatorId);
        values.put(STUDENT_ID, studentId);
        values.put(STUDENT_NAME_SCAN, studentNameDetails);
        values.put(SCHOOL_NAME_SCAN, schoolNameDetails);
        values.put(PAPER, paper);

        // Inserting Row
        long id = db.insert(STUDENT_SCAN_DETAILS, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New Student Scan Details inserted into sqlite: " + id);
    }


    //Update Scanned Student Details table

    public void updateStudentDetails(int paper){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PAPER, paper);

      db.update(STUDENT_SCAN_DETAILS, values, "PAPER= ?",  new String[] {});
                db.close(); // Closing database connection
        Log.d(TAG, "New Student Scan Details updated into sqlite: ");
    }

    // Add Malpractice data
    public void addMalpracticeData(String malpracticeInvigilatorId, String malpracticeStudentId, String malpracticeType, int malpracticePaper,int status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MALPRACTICE_INVIGILATOR_ID, malpracticeInvigilatorId);
        values.put(MALPRACTICE_STUDENT_ID, malpracticeStudentId);
        values.put(MALPRACTICE_TYPE, malpracticeType);
        values.put(MALPRACTICE_PAPER, malpracticePaper);
        values.put(STATUS, status);

        // Inserting Row
        long id = db.insert(MALPRACTICE_DETAILS, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New Malpractice Details inserted into sqlite: " + id);
    }

    public void addCoordinatorDetails( String coordinatorId, String invigilatorName, String examNo ){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COORDINATOR_ID_DETAILS, coordinatorId);
        values.put(INVIGILATOR_NAME, invigilatorName);
        values.put(EXAM_NO, examNo);
        values.put(TIMESTAMP, " time('now') " );

        // Inserting Row
        long id = db.insert(COORDINATOR_DETAILS, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New Coordinator Details inserted into sqlite: " + id);

    }


    public void saveAttendanceDetails(String attendanceSchoolName, String attendanceCandidateName){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ATTENDANCE_SCHOOL_NAME, attendanceSchoolName);
        values.put(ATTENDANCE_SCHOOL_NAME, attendanceCandidateName);
        values.put(ATTENDANCE_TIMESTAMP, getDateTime());

        // Inserting Row
        long id = db.insert(ATTENDANCE, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New Attendance Details inserted into sqlite: " + id);
    }

    public void saveExamDetails(String examSchoolName, long examPaper){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EXAM_SCHOOL_NAME,examSchoolName );
        values.put(EXAM_TIME, getDateTime());
        values.put(EXAM_PAPER, examPaper);
        // Inserting Row
        long id = db.insert(START_EXAM, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New Exam Details inserted into sqlite: " + id);

    }



    public Cursor query(String query){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(query, null);
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

}//class
