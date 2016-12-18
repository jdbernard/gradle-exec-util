package com.jdbernard.gradle

// ## Utility methods for working with processes.
public class ExecUtil {

  public static void exec_(List<String> cmd) { exec(cmd, null, false) }
  public static void exec_(String... cmd) { exec(cmd, null, false) }
  public static void exec(String... cmd) { exec(cmd, null, true) }

  public static void exec(List<String> cmd, File workingDir, boolean checkExit) {
      exec(cmd as String[], workingDir, checkExit) }

  public static void exec(String[] cmd, File workingDir, boolean checkExit) {
    def pb = new ProcessBuilder(cmd)
    if (workingDir) pb.directory(workingDir)
    def process = pb.start()
    process.waitForProcessOutput(System.out, System.err)

    if (process.exitValue() != 0)
      println "Command $cmd exited with non-zero exit value: ${process.exitValue()}."
    if (checkExit) assert process.exitValue() == 0 : "Not ignoring failed command." }

  public static void execAll(List<List<String>> cmds, File workingDir, boolean checkExit) {

    // Kick off all processes
    def processes = cmds.map {
      ProcessBuilder pb = new ProcessBuilder(it)
      if (workingDir) pb.directory(workingDir)
      pb.start() }

    // Wait for each process to complete
    processes.each {
      it.waitForProcessOutput(System.out, System.err)
      if (it.exitValue() != 0)
        println "Command $cmd exited with non-zero exit value: ${it.exitValue()}."

      if (checkExit)
        assert process.exitValue() == 0 : "Not ignoring failed command." } }

  public static void spawn(String... cmd) { spawn(cmd, null) }
  public static void spawn(List<String> cmd, File workingDir) { spawn(cmd as String[], workingDir) }
  public static void spawn(String[] cmd, File workingDir) {
    def pb = new ProcessBuilder(cmd)
    if (workingDir) pb.directory(workingDir)
    def process = pb.start() }
}
