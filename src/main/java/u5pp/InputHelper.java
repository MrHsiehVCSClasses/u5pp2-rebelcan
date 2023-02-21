package u5pp;

import java.util.ArrayList;
import java.util.Scanner;

public class InputHelper
{
    InputHelper inputHelper;
    Scanner scanner;
    ArrayList<String> input_list;


    public InputHelper(Scanner scanner) {
        this.scanner=scanner;
        input_list=new ArrayList<>();
    }


    //input to board coordinate
    public int[] getChessLocation(String prompt)
    {
        int []output=new int[2];
        boolean is_correct=false;

        if(input_list.size()==0)
        {
            while (scanner.hasNextLine())
            {
                //input
                String input_str = scanner.nextLine();
                if(input_str.isEmpty())
                    break;

                if (input_str.length() != 2)
                    continue;

                char first = input_str.charAt(0);
                char second = input_str.charAt(1);

                if (('a' <= first && first <= 'h') && ('1' <= second && second <= '8'))
                {
                    input_list.add(input_str);
                    is_correct=true;
                }
            }
        }
        else
        {
            String next_input=input_list.get(0);
            input_list.remove(0);

            char first = next_input.charAt(0);
            char second = next_input.charAt(1);
            output[1] = (int)(first-97);
            output[0] = second - '0'-1;
        }

        if(is_correct)
        {
            String next_input=input_list.get(0);
            input_list.remove(0);

            char first = next_input.charAt(0);
            char second = next_input.charAt(1);
            output[1] = (int)(first-97);
            output[0] = second - '0'-1;
        }

        return output;
    }
}

