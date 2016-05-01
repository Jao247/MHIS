package csis.ie.ul.mhis.objects;

public class BossObj
{
    private int    _id;
    private String _name;
    private String _type;
    private String _weakness;
    private String _element;

    public BossObj(int _id, String _name, String _type, String _weakness, String _element)
    {
        this._id = _id;
        this._name = _name;
        this._type = _type;
        this._weakness = _weakness;
        this._element = _element;
    }

    public int get_id() { return _id; }

    public void set_id(int _id) { this._id = _id; }

    public String get_name() { return _name; }

    public void set_name(String _name) { this._name = _name; }

    public String get_type() { return _type; }

    public void set_type(String _type) { this._type = _type; }

    public String get_weakness() { return _weakness; }

    public void set_weakness(String _weakness) { this._weakness = _weakness; }

    public String get_element() { return _element; }

    public void set_element(String _element) { this._element = _element; }
}
