package samples
import com.soywiz.korge.input.DraggableCloseable
import com.soywiz.korge.input.draggableCloseable
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.ui.uiCheckBox
import com.soywiz.korge.view.SContainer
import com.soywiz.korge.view.solidRect

class MainDraggable : Scene() {
    override suspend fun SContainer.sceneMain() {
        val draggableRect = solidRect(100, 100)

        var closeable: DraggableCloseable? = null

        uiCheckBox(text = "Toggle dragging") {
            onChange {
                if (it.checked) {
                    closeable = draggableRect.draggableCloseable()
                } else {
                    closeable?.close()
                }
            }
        }
    }
}
