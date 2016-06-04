package de.szut.dqi14.gahr.Runlength;

import org.apache.commons.cli.ParseException;

import java.io.IOException;

class MainRunlenght extends Rl3 {

	public static void main(String[] args) throws RLedException, IOException, ParseException {
        /* this class calls the selected runlength-class
        * it also throws a RLedException at any error*/
        try {
            Param.param(args);
        } catch (ParseException e) {
            if (!Param.quiet) {
                throw new RLedException(1);
            } else {
                System.out.println("Error");
                System.exit(0);
            }
        } catch (Exception e) {
            if (!Param.quiet) {
                throw new RLedException(5);
            } else {
                System.out.println("Error");
                System.exit(0);
            }
        }

        if (Param.rl2){
            Rl2 myRl2 = new Rl2();
            try {
                myRl2.callCompression();
            } catch (Exception e) {
                if (!Param.quiet) {
                    throw new RLedException(5);
                } else {
                    System.out.println("Error");
                    System.exit(0);
                }
            }
        }

		else if (Param.rl3){
            Rl3 myRl3 = new Rl3();
            try {
                myRl3.callCompression();
            } catch (Exception e) {
                if (!Param.quiet) {
                    throw new RLedException(5);
                } else {
                    System.out.println("Error");
                    System.exit(0);
                }
            }
        }
	}
}
