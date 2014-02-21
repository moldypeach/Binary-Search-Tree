/* Filename:        Student_Sig_Block.java
 * Last Modified:   21 Feb 2014
 * Author:          Todd Parker
 * Email:           todd.i.parker@maine.edu
 * Course:          CIS314 - Advanced Java
 * 
 * NOTE: Adapted from CIS214
 */
package binarysearchtree;

public class Student_Sig_Block
{    
    // overrides superclass class Object's toString method
    public static void printSig()
    {
        // Declare variables to create ascii border for signature block
        String border = "";
        int border_Characters = 52;
        // Declare and initialize variables to hold student/course information
        String student_Name = "Todd Parker";
        String student_Email = "todd.i.parker@maine.edu";
        String course_Info = "CIS314 Advanced Java Programming";
        
        // Use a for loop structure to populate the border variable with an
        // 80 character line of *'s. Loop utilizes string concatenation.
        for (int i = 0; i < border_Characters; ++i)
        {
            border += "*";
        }
        // Print sig-block output as five strings, separated by newlines
        System.out.printf("\n\n%s\n%s\n%s\n%s\n%s\n\n", border, student_Name, student_Email,
                course_Info, border);
    } // end method toString
} // end class Student_Sig_Block
