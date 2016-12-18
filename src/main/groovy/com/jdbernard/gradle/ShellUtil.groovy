package com.jdbernard.gradle

// ## Utility methods for working with processes.
public class ShellUtil {

  public static void shell_(List<String> cmd) { shell(cmd, null, false) }
  public static void shell_(String... cmd) { shell(cmd, null, false) }
  public static void shell(String... cmd) { shell(cmd, null, true) }

  public static void shell(List<String> cmd, File workingDir, boolean checkExit) {
      shell(cmd as String[], workingDir, checkExit) }

  public static void shell(String[] cmd, File workingDir, boolean checkExit) {
      def pb = new ProcessBuilder(cmd)
      if (workingDir) pb.directory(workingDir)
      def process = pb.start()
      process.waitForProcessOutput(System.out, System.err)

      if (process.exitValue() != 0)
          println "Command $cmd exited with non-zero result code."
      if (checkExit) assert process.exitValue() == 0 : "Not ignoring failed command." }

  public static void shell(List<List<String>> cmds, File workingDir) {
      cmds.each {
          ProcessBuilder pb = new ProcessBuilder(it)
          pb.directory(workingDir)
          pb.start().waitForProcessOutput(System.out, System.err) } }

  public static void spawn(String... cmd) { spawn(cmd, null) }
  public static void spawn(List<String> cmd, File workingDir) { spawn(cmd as String[], workingDir) }
  public static void spawn(String[] cmd, File workingDir) {
      def pb = new ProcessBuilder(cmd)
      if (workingDir) pb.directory(workingDir)
      def process = pb.start() }
}
