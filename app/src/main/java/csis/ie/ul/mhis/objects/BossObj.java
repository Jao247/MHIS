package csis.ie.ul.mhis.objects;


public class BossObj
{
    private int _id;
    private String _name;
    private String _type;
    private String _weakness;
    private String _element;
    private String _url;

    public BossObj(int _id, String _name, String _type, String _weakness, String _element, String _url) {
        this._id = _id;
        this._name = _name;
        this._type = _type;
        this._weakness = _weakness;
        this._element = _element;
        this._url = _url;
    }

    public int get_id() { return _id; }

    public String get_name() { return _name; }

    public String get_type() { return _type; }

    public String get_weakness() { return _weakness; }

    public String get_element() { return _element; }

    public String get_url() {return _url;}
}
