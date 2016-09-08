package com.merzayc.viewpager.util;



public class Note {


    int _id;
    String _title;
    String _data;
    Long _date;
    int _icon;


    public Note() {
    }



    public Note(int id, String title, String _data, Long _date, int _icon) {
        this._id = id;
        this._title = title;
        this._data = _data;
        this._date = _date;
        this._icon = _icon;


    }






    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public String get_data() {
        return _data;
    }

    public void set_data(String _data) {
        this._data = _data;
    }

    public Long get_date() {
        return _date;
    }

    public void set_date(Long _date) {
        this._date = _date;
    }

    public int get_icon() {
        return _icon;
    }

    public void set_icon(int _icon) {
        this._icon = _icon;
    }

}


