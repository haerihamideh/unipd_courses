# Search Engines (SE) - Repository Template

This repository is a repository for the homeworks to be developed in the [Search Engines](https://iiia.dei.unipd.it/education/search-engines/) course.

The homeworks are carried out by groups of students and consists in participating to one of the labs organized yearly by [CLEF](https://www.clef-initiative.eu/) (Conference and Labs of the Evaluation Forum).

*Search Engines* is a course of the

* [Master Degree in Computer Engineering](https://degrees.dei.unipd.it/master-degrees/computer-engineering/) of the  [Department of Information Engineering](https://www.dei.unipd.it/en/), [University of Padua](https://www.unipd.it/en/), Italy.
* [Master Degree in Data Science](https://datascience.math.unipd.it/) of the  [Department of Mathematics "Tullio Levi-Civita"](https://www.math.unipd.it/en/), [University of Padua](https://www.unipd.it/en/), Italy.

*Search Engines* is part of the teaching activities of the [Intelligent Interactive Information Access (IIIA) Hub](http://iiia.dei.unipd.it/).

### Organisation of the repository ###

The repository is organised as follows:

* `code`: this folder contains the source code of the developed system.
    * `OriginalData` : The original data from CLEF
        * `English`
        * `French`
    * `ProcessedOutput` : The sanitized files from OriginalData
        * `English`
        * `French`
    * `semicolon`
        * `indexed`
          * `French`
          * `English`
* `javadoc`
* `src`
    * `main`
        * `java`
            * `analyze`
            * `index`
            * `parse`
            * `search`
            * `runFile`
            * `rankFusion`
        * `resources`
* `runs`: this folder contains the runs produced by the developed system.
* `results`: this folder contains the performance scores of the runs.
* `homework-1`: this folder contains the report describing the techniques applied and insights gained.
* `homework-2`: this folder contains the final paper submitted to CLEF.
* `slides`: this folder contains the slides used for presenting the conducted project.

## Project Description ##

The project goal is to retrieve a pair of coherent sentences through an online debate portals dataset which are related to a specific topic.

The project's structure is comprised of four major components: **analyze**, **indexer**, **parser**, and **searcher**.
During the runtime, the input directory which OriginalData for English or French is prepared using the **Eliminator**
which is maintained in the parser part, and it sanitizes the original data to make it more ready for indexing,
then it gets indexed with **HelloQwant**, and the searcher matches the queries in the **qrel** file and the documents.
The parsed documents are then tokenized by **HelloQwant** while **DirectoryIndexer** attempts to index the documents.
At this point, when Lucene has finished indexing (which takes a few minutes),
The **Searcher** searches the indexed collection for a rating of documents for each of the possible assessment subjects (queries). The **runFile.java** method in the code folder is in responsible of carrying out all of the preceding stages.

## How to run and use the codes? ##

* Before proceeding, ensure that the **Original Data** files are included on your system. Then, run the **runFile** and the intellij software will begin to execute and preprocess the files and sanitize them, after which the indexing portion will begin, and when the searching portion has concluded, you will see the run file in the **semicolon** folder.
* **WARNING** HelloQwant prefers that the document be in list format (with only one line), thus you should execute the eliminator with a 'true' option in 'writeInOneLine'.
* You may also do both because there are two ways to run the project:
* First you can simply run the **runFile**
* For the manual method, first run the **Eliminator**, then go to the **HelloQwant** and run it for the indexing phase, and then run the **Searcher** to search for topics.
* For using different analyzers you have to change some parameters to get the result
  * For running with `ngrams` change the analyzer to `NGramAnalyzer(min, max)` and there are 3 different Ngram min and max which are about `(2,3)` `(3,4)` `(4,5)` that are usable.
  * Also for the `porter1` & `porter2` changes should be included for running. 
  * For running `porter1` set the analyzer for `CustomAnalyzer` and add the `.addTokenFilter("lowercase")` ,`.addTokenFilter("stop")` , `.addTokenFilter("porterstem")` , `.addTokenFilter("capitalization")`
  to get the best possible result in `porter1` the similarity should be changed to `BM25Similarity((float) 1.3, (float) 0.75)`.
  * To run the `porter2` change the analyzer to `Porter2Analyzer` and for the best possible result set the similarity set it exactly like `porter1` Similarity "BM25Similarity()".
* After running different methods there is another step for `RankFusion`. Now the list of all the mentioned runs for ranking are available RunFusion is the end stem for have the best result
on the runs. To start this step first it is compulsory to have all the runs for it (for example ngrams and all other runs) and then in the list it is mandatory to add the runs like below.
  `"Fpga_analyzer_fpga_searcher_ID*
  "KStemFilterAnalyzer_ID",
  "semicolon-custom-with-syn1-idx-custom-with-syn1-srch"
  "seupd2223-semicolon-KStemAnalyzer3-4-english",
  "Seupd2223-semicolon-KStemAnalyzer3-4-whitespace"
  "seupd2223-semicolon-ngram3-4-Dirichlet"
  "soupd2223-semicolon-ngramAnalyzer3-4-english",
  "Seupd2223-semicolon-ngramAnalyzer4-5-engLish"
  "Seupd2223-semicolon-ngramAnalyzer4-5-english",
  "seupd2223-semicolon-ngramAnalyzer-english",
  "Seupd2223-semicolon-porter2stem-english",
  "Soupd2223-semicolon-porterstem2-english-1p8",
  *seupd2223-semicolon-porterstem2-english-3ps"
  "seupd2223-semicolon-pontersten-drichlet",
  "seupd2223-semicoton-porterstem-drichlet-1000"
  "seupd2223-semicolon-porterstem-english",
  "seupd2223-semicolon-porterstem-english1p3",
  "Seupd2223-semicolon-snowballPorter3-4-standard",
  "seupd2223-semicolon-basic-eng"`
* At last, it is possible to change the directories for RankFusion
  * String base = "the path of the run file";
  * String outputFilePath = base + "new path for rank file";
  * String fusedRankName = runID
* then run the rank fusion and see the result


### License ###

All the contents of this repository are shared using the [Creative Commons Attribution-ShareAlike 4.0 International License](http://creativecommons.org/licenses/by-sa/4.0/).

![CC logo](https://i.creativecommons.org/l/by-sa/4.0/88x31.png)

