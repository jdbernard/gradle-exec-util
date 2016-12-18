## Gradle Exec Utilities

A small set of methods to make it easy to execute other things from a gradle
script without needing to create a dedicated tasks for each invocation.

### API

`com.jdbernard.gradle.ExecUtil` exposes a set of static methods. The two
primary methods are:

* `void exec(String[] cmd, File workingDirectory, boolean checkExit)`

  Execute the command specified by `cmd` (each argument as a separate string).
  The process' output and error streams are redirected to `System.out` and
  `System.err`. This call will block until the sub-process exits.

  `workingDirectory` defines the process' working directory. If `null`, the
  process inherits the current working directory.

  If `checkExit` is true, `exec` uses a Groovy assertion to check that the exit
  value of the executed process is zero (fails the assertion if it is not).

  Other flavors of this method:

  - `void exec(List<String> cmd, File workingDir, boolean checkExit)`,
  - `void exec(String... cmd)` - sets `workingDir` to `null` and `checkExit` to `true`,
  - `void exec_(List<String> cmd)` - sets `workingDir` to `null` and `checkExit` to `false`,
  - `void exec_(String... cmd)` - sets `workingDir` to `null` and `checkExit` to `false`,
  - `void execAll(List<List<String>> cmds, File workingDir, boolean checkExit)` -
     convenience method to kick off multiple processes at once.

* `void spawn(String[] cmd, File workingdir)`

  Similar to `exec`, except the call returns immediately and does not wait for
  the sub-process to complete. Becuase of this it cannot check the sub-process
  exit value.

  Other flavors:

  - `spawn(List<String> cmd, File workingDir)`
  - `spawn(String... cmd)` - sets `workingDir` to null

### Example Usage

```groovy
import static com.jdbernard.gradle.ExecUtil.*

// Execute: 'ls -al', ignoring the exit value
exec_('ls', '-al')

// Execute 'scss --update src.scss:dest.css', checking that the return is 
exec('scss', '--update', 'src.scss:dest.css')
```
