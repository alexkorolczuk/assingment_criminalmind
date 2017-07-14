package com.derrick.park.criminalmind;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.derrick.park.criminalmind.database.CrimeBaseHelper;
import com.derrick.park.criminalmind.database.CrimeCursorWrapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.derrick.park.criminalmind.database.CrimeDbSchema.*;
import static com.derrick.park.criminalmind.database.CrimeDbSchema.CrimeTable.*;

/**
 * Created by park on 2017-06-01.
 */

public class CrimeLab {

    private static CrimeLab sCrimeLab;

    //!!!!!!!!!!!!!!!!!!!!!!!!
    private Context mContex;
    private SQLiteDatabase mDatabase;

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context) {
        // database!!!!!!!!!!!

        mContex = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContex).getWritableDatabase();


    }

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // wywalamy listy
private static ContentValues getContentValues(Crime crime){
    ContentValues values = new ContentValues();
    values.put(Cols.UUID, crime.getId().toString());
    values.put(Cols.TITLE, crime.getTitle());
    values.put(Cols.DATE, crime.getDate().getTime());
    values.put(Cols.SOLVED, crime.isSolved() ? 1 : 0);
    values.put(Cols.SUSPECT, crime.getmSuspect());


    return values;
}

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            public void addCrime(Crime c){
            ContentValues values = getContentValues(c);
                mDatabase.insert(CrimeTable.NAME, null, values);
            }

            public void updateCrime(Crime crime){
                String uuidString = crime.getId().toString();
                ContentValues values = getContentValues(crime);
                mDatabase.update(CrimeTable.NAME, values, Cols.UUID + " = ?", new String [] {uuidString});
            }

private CrimeCursorWrapper queryCrimes (String whereClause, String [] whereArgs){
    Cursor cursor = mDatabase.query(CrimeTable.NAME, null, whereClause, whereArgs, null, null, null);
    return new CrimeCursorWrapper(cursor);
}
            public void deleteCrime(Crime crime){
                String uuidString = crime.getId().toString();
                mDatabase.delete(CrimeTable.NAME, Cols.UUID + " = ?", new String[] {uuidString});

            }



                public List<Crime> getCrimes() {
                    List <Crime> crimes = new ArrayList<>();
                    CrimeCursorWrapper cursor = queryCrimes(null, null);

                    try {
                        cursor.moveToFirst();
                        while (!cursor.isAfterLast()){
                            crimes.add(cursor.getCrime());
                            cursor.moveToNext();
                        }
                    } finally {
                        cursor.close();
                    }
                    return crimes;
                }

                public Crime getCrime(UUID id){
                    CrimeCursorWrapper cursor = queryCrimes(Cols.UUID + " = ?", new String[] {id.toString()});

                    try {
                        if (cursor.getCount() == 0){
                            return null;
                        }
                        cursor.moveToFirst();
                        return cursor.getCrime();
                    }finally {
                        cursor.close();
                    }

                }


    public File getPhotoFile(Crime crime){
        File filesDir = mContex.getFilesDir();
        return new File(filesDir, crime.getPhotoFileName());

    }

}


//!!!!!!!!!!!!!!!!










