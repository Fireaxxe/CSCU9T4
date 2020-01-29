
/**
 * Driver class for the T4 security lab.
 * 
 * @author nve
 * @version 0.1
 */
public class LabMain
{
    public LabMain() {}

    public static void main(String[] args)
    {
        try
        {
           LabSecurity lab = new LabSecurity(); 
           
           lab.tryMD5();
           
           lab.bruteForce("file");
           
           System.out.println(lab.generateHash("test"));            
           
        } catch (Exception e) {
        }
    }
}
