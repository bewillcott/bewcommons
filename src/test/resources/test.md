
Has Named Groups:

|Group Name||Description|Empty Value|
|---------:|-| :------: |  --:-----|
|endTag|:|Captures the '/' if this is an end tag|null|
|tagName|:|Captures TagName|Required - never empty|
|atts|:|Group attributes|"" - Empty String|
|att|:|A single attribute|null|
|attName|:|Attribute name|null|
|attVal1|:|"Double quoted" value string *|null|
|attVal2|:|'Single quoted' value string *|null|
|completeTag|:|Captures the '/' if this is a complete tag|null|

**\*** Normally only _attVal1_ will have content.

- Added more protocols:
    - ftp
    - file

- Added Named Groups:
    ("{@code http://www.sample.com/this/directory/path/file.html}")
    - protocol: "{@code http}"
    - website : "{@code www.sample.com}"
    - filepath : "{@code /this/directory/path/file.html}"


New column alignments:

| Default | Left   | Center | Right  | Justify |
| ------- | :----- | --:--- | -----: | :-----: |
| Something | to   | look   | forward | to right? |
| You     | are    | going  | to     | love this Freddy. |
| Why     | don't  | you    | play   | nicely with others? In my younger and more vulnerable years my father gave me some advice that I've been turning over in my mind ever since. 'Whenever you feel like criticizing anyone,' he told me, 'just remember that all the people in this world haven't had the advantages that you've had.'|
| Hi|In my younger and more vulnerable years my father
 gave me some advice that I've been turning over in my mind ever since. 
'Whenever you feel like criticizing anyone,' he told me, 
'just remember that all the people in this world haven't had the 
advantages that you've had.'||||
