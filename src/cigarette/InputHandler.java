package cigarette;

import java.util.Scanner;

/**
 * Created by annakertesz on 10/24/16.
 */
public class InputHandler {
//    Scanner scan = new Scanner(System.in);
//    int i = scan.nextInt();
//    int j = scan.nextInt();

    public int[] inputData() {

        Scanner dd = new Scanner(System.in);
        int[] data = new int[2];

        for(int i = 0; i < data.length; i++) {
            System.out.println("Enter next var: ");
            data[i] = dd.nextInt();
        }
//        System.out.println("mood?");
//        InputHandler ih = new InputHandler();
//
//        int[] data = {ih.i, ih.j};
        return data;
    }

}
