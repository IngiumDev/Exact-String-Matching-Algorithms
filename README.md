# Exact String Matching Algorithms Comparison
## What is String Matching?
String matching is the process of finding a substring in a string. There are many different algorithms for string matching. The naive approach is to compare each character of the pattern to each character of the sequence. The KMP approach is to create a prefix table and then use that to skip characters in the sequence. The Boyer Moore approach is to create a bad character table and a good suffix table and then use those to skip characters in the sequence.
## What is the time complexity of the naive approach?
The time complexity of the naive approach is O(mn) where m is the length of the pattern and n is the length of the sequence.
## What is the time complexity of the KMP approach?
The time complexity of the KMP approach is O(m + n) where m is the length of the pattern and n is the length of the sequence. 
## What is the time complexity of the Boyer Moore approach?
The time complexity of the Boyer Moore approach is O(mn) where m is the length of the pattern and n is the length of the sequence.
## Why do we use Boyer Moore instead of KMP?
Even though Boyer Moore has a worse time complexity than KMP, it is faster in practice because it skips more characters i.e. it is more efficient on average. However, KMP is better when we have a small alphabet of possible characters e.g. DNA.

This project is a quick implementation of all three approaches. It creates a random sequence and pattern and then tests each approach. It then prints out which approach was the fastest and by how many nanoseconds it was faster.