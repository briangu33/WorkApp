# WorkApp
This is a command line productivity tool for managing tasks. It was written in about 12 hours for [Alex Zhu](https://github.com/zhukeepa)'s Alphasheets coding challenge (instructions reproduced below with permission).

To run:

1) Move “work” (the script) into your bin folder (then run <code>$chmod +x work</code>)

2) Move “WorkApp” into your home directory.

3) Run <code>work</code> or <code>work email</code> from command line (see below for what these do).

Source code in WorkApp/src.

<h1>Background</h1>
<p>I sometimes randomly want to jot down tasks over the course of a day, categorized by project. I&#39;d like a console application to help me do that. I want it to help me maintain a file like this: </p>
<pre><code>*** Project1 <span class="hljs-comment">-- http://linktoproject1records.com</span>
<span class="hljs-keyword">Task</span> <span class="hljs-number">1</span>
<span class="hljs-keyword">Task</span> <span class="hljs-number">2</span>

*** Project2 <span class="hljs-comment">-- http://linktoproject2records.com</span>
<span class="hljs-keyword">Task</span> <span class="hljs-number">3</span>
<span class="hljs-keyword">Task</span> <span class="hljs-number">4</span>
</code></pre><p>Maintaining a file like this manually is cumbersome. I <em>should</em> just be able to open a terminal and write <code>#project1 Task 5</code> and have Task 5 automatically added under #project1, like so: </p>
<pre><code>*** Project1 <span class="hljs-comment">-- http://linktoproject1records.com</span>
<span class="hljs-keyword">Task</span> <span class="hljs-number">1</span>
<span class="hljs-keyword">Task</span> <span class="hljs-number">2</span>
<span class="hljs-keyword">Task</span> <span class="hljs-number">5</span>

*** Project2 <span class="hljs-comment">-- http://linktoproject2records.com</span>
<span class="hljs-keyword">Task</span> <span class="hljs-number">3</span>
<span class="hljs-keyword">Task</span> <span class="hljs-number">4</span>
</fieldset>
</code></pre><h1 id="instructions">Instructions</h1>
<p>I&#39;d like you to write a console application named <strong><code>work</code></strong> that reads/writes from a file <code>tasks.txt</code> so that, when you run it in the terminal by typing <code>work</code>: </p>
<ul>
<li>if I type <code>#stuff Words words words</code>, and press enter, it checks if the project <code>stuff</code> exists in the text file, and: <ul>
<li>if yes, adds <code>Words words words</code> under that section</li>
<li>if no, tell me that project doesn&#39;t exist, and ask me if I&#39;d like to create that project, and ask for a response of either <code>y</code> or <code>n</code>. <ul>
<li>if <code>y</code>: create that project in the text file, with the task under it, formatted as above, with no link: <ul>
<li><code>*** stuff --</code></li>
<li><code>Words words words</code></li>
<li>(Disregard the bullet points, I don&#39;t know how to format the above without the bullet points)</li>
</ul>
</li>
<li>if <code>n</code>: exit this routine, and go back to accepting more commands</li>
</ul>
</li>
</ul>
</li>
<li><p>if I type <code>setUrl #projectname google.com</code> and press enter: </p>
<ul>
<li>if project <code>projectname</code> exists, replace whatever is after the URL for it in the file with google.com. So, if the entry in the text file was <pre><code>*** projectname <span class="hljs-comment">-- somewebsite.com</span>
</code></pre>the entry in the file would now be <pre><code>*** projectname <span class="hljs-comment">-- google.com</span>
</code></pre></li>
<li>if it doesn&#39;t exist, ask me if I want to create that project, and ask for a response of either <code>y</code> or <code>n</code>. If <code>y</code>, add a new project to the file, if <code>n</code>, exit this routine and go back to accepting more commands. </li>
</ul>
</li>
<li><p>if the file <code>task.txt</code> does not already exist when the app exists, create it (and leave it blank)</p>
</li>
<li><p>if my project name is <code>urgent</code> (e.g. if I write <code>#urgent buy milk</code>), do <em>not</em> add the thing I write to a file: instead, send an email to zhukeepa@gmail.com with subject line &quot;URGENT TASK&quot;, and with body whatever the task is (in this case, &quot;buy milk&quot;). (You can send from any email address.)</p>
</li>
<li><p>if <code>email</code> is passed in as a command line argument, <em>don&#39;t</em> do any of the above. Instead, email the contents of tasks.txt to zhukeepa@gmail.com, copy tasks.txt to a file called oldtasks.txt, and remove all the tasks under each project (but keep the projects and URL&#39;s the same). So for example, if tasks.txt is: </p>
</li>
</ul>
<pre><code>*** Project1 <span class="hljs-comment">-- http://linktoproject1records.com</span>
<span class="hljs-keyword">Task</span> <span class="hljs-number">1</span>
<span class="hljs-keyword">Task</span> <span class="hljs-number">2</span>
<span class="hljs-keyword">Task</span> <span class="hljs-number">5</span>

*** Project2 <span class="hljs-comment">-- http://linktoproject2records.com</span>
<span class="hljs-keyword">Task</span> <span class="hljs-number">3</span>
<span class="hljs-keyword">Task</span> <span class="hljs-number">4</span>
</code></pre><p>and I run <code>work email</code> in the command line, the above contents would get emailed to me, the above contents would get copied to a file called oldtasks.txt, and the new tasks.txt would be: </p>
<pre><code>*** Project1 -- <span class="hljs-string">http:</span><span class="hljs-comment">//linktoproject1records.com</span>

*** Project2 -- <span class="hljs-string">http:</span><span class="hljs-comment">//linktoproject2records.com</span>
</code></pre><p>and after that, the program <code>work</code> would <em>not</em> be running. </p>
