package com.zetcode

import generateCharacter
import getResults
import java.awt.EventQueue
import javax.swing.GroupLayout
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JFrame
import javax.swing.JTextArea

import kotlin.system.exitProcess

class CloseButtonEx(title: String) : JFrame() {

    init {
        createUI(title)
    }

    private fun createUI(title: String) {

        setTitle(title)

        val button = JButton("Roll!")
        val textArea = JTextArea(20, 50) // Rows, Columns
        textArea.lineWrap = true
        textArea.isEditable = false // Set to false to prevent user editing

        button.addActionListener {
            val results = getResults() // Call getResults to generate results
            textArea.text = results.toString() // Set text to results string
        }

        createLayout(button, textArea)

        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(1366, 768)
        setLocationRelativeTo(null)
    }


    private fun createLayout(vararg arg: JComponent) {

        val gl = GroupLayout(contentPane)
        contentPane.layout = gl

        gl.autoCreateContainerGaps = true

        gl.setHorizontalGroup(
            gl.createSequentialGroup()
                .addComponent(arg[0])
                .addComponent(arg[1])
        )

        gl.setVerticalGroup(
            gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(arg[0])
                .addComponent(arg[1])
        )

        pack()
    }
}

private fun createAndShowGUI() {

    val frame = CloseButtonEx("Super Atomic Character Generator")
    frame.isVisible = true
}

fun main() {
    EventQueue.invokeLater(::createAndShowGUI)
}
