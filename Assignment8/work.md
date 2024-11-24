


positive and negative:

Keys: terms in each tweet

Values : term frequency

note*
if Happy occurs 10 times - tweet is happy
    put in positive map
    happy:10

if Sad occurs twice - tweet is negative
    put in negative map
    sad : 2

Always update a term's frequency


function for removing non-alphabetic terms: 
    term = Pattern.compile("[^a-zA-Z\\s]").matcher(term).replaceAll("");

    