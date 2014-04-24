Binary-Search-Tree
==================

Generic Data Structures - Exercises: 22.17, 22.22

Your assignment is to complete exercises 22.17 and 22.22 in the Deitel and Deitel book.  In the first exercise you will 
construct a binary search tree that accepts a line of text, tokenizes it, and then inserts the tokens into the tree.  
You must then write in-order, pre-order and post-order traversals of the tree printing out the sentence in each of these
three orders.  For each token, you should also keep a count of the number of occurrences of that token. The sentence you 
will use to demonstrate the program is this one (from Edgar Allen Poe’s “Fall of the House of Usher”):

    DURING the whole of a dull, dark, and soundless day in the autumn of the year, when the clouds hung oppressively low
    in the heavens, I had been passing alone, on horseback, through a singularly dreary tract of country; and at length
    found myself, as the shades of the evening drew on, within view of the melancholy House of Usher.
    
The sentence has been chosen to be long and to have a variety of punctuation and capitalization to test against. 
Punctuation should not result in a change in token – so “on” and “on,” should result in the same token. You should be 
able to handle this situation using the regular expression in your string split. Once you have the program creating 
the tree, you will complete exercise 22.22 in which you will delete all references to the word “of” from the tree.  
Provide two methods of deleting – one which deletes the node no matter what its count was, and one that decrements the 
count by one (deleting the node only when the count falls below one).

Demonstrate your program’s capabilities with the following test cases:
•	Delete entire single count word
•	Delete by decrementing a single count word
•	Delete entire multiple count word
•	Decrement the value of a multiple count word
•	Repeat the inorder, preorder and postorder traversals to show the effects

Upload all source code, an executable .jar file and output demonstrating each of the required capabilities to the class web site prior to the due date listed above.
To check your work, here are the results I get before deletes occur:

Preorder traversal
DURING(1) the(8) of(6) a(2) I(1) House(1) Usher(1) dull(1) dark(1) and(2) alone(1) autumn(1) at(1) as(1) clouds(1) been(1) country(1) day(1) dreary(1) drew(1) in(2) hung(1) heavens(1) had(1) found(1) evening(1) horseback(1) low(1) length(1) myself(1) melancholy(1) soundless(1) oppressively(1) on(2) passing(1) singularly(1) shades(1) whole(1) when(1) through(1) tract(1) view(1) year(1) within(1) 

Inorder traversal
DURING(1) House(1) I(1) Usher(1) a(2) alone(1) and(2) as(1) at(1) autumn(1) been(1) clouds(1) country(1) dark(1) day(1) dreary(1) drew(1) dull(1) evening(1) found(1) had(1) heavens(1) horseback(1) hung(1) in(2) length(1) low(1) melancholy(1) myself(1) of(6) on(2) oppressively(1) passing(1) shades(1) singularly(1) soundless(1) the(8) through(1) tract(1) view(1) when(1) whole(1) within(1) year(1) 

Postorder traversal
House(1) Usher(1) I(1) alone(1) as(1) at(1) been(1) country(1) clouds(1) autumn(1) and(2) drew(1) dreary(1) day(1) dark(1) evening(1) found(1) had(1) horseback(1) heavens(1) hung(1) length(1) melancholy(1) myself(1) low(1) in(2) dull(1) a(2) on(2) shades(1) singularly(1) passing(1) oppressively(1) soundless(1) of(6) view(1) tract(1) through(1) when(1) within(1) year(1) whole(1) the(8) DURING(1)

