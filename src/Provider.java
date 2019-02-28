
public class Provider
{
    String name, field;
    public Provider(String n, String f)
    {
        name = n;
        field = f;
    }
    
    public String getName(){
        return name;
    }
    
    public String getField(){
        return field;
    }
    
    public String toString(){
        return name + " (" + field + ")";
    }
}
