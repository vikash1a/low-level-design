package org.example.designpattern.creational

interface Button {
    fun render(component: String)
    fun onClick(component: String)
}

class WindowsButton : Button {
    override fun render(component: String) = println("Windows button rendered-$component")
    override fun onClick(component: String) = println("Windows button clicked-$component")
}

class HtmlButton : Button {
    override fun render(component: String) = println("Html button rendered-$component")
    override fun onClick(component: String) = println("Html button clicked-$component")
}

abstract class Dialog {
    abstract fun createButton(): Button
    fun render() {
        val button = createButton()
        button.onClick("open")
        button.render("new")
    }
}

class WindowsDialog : Dialog() {
    override fun createButton(): Button = WindowsButton()
}

class HtmlDialog : Dialog() {
    override fun createButton(): Button = HtmlButton()
}
