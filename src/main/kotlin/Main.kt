import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.useResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {
    val icon = BitmapPainter(useResource("sample.png", ::loadImageBitmap))
    val mainWindowState = rememberWindowState()
    var showMainWindow by remember { mutableStateOf(true) }
    var showSecondWindow by remember { mutableStateOf(false) }

    if (showMainWindow) {
        MainWindow(icon,mainWindowState,{showMainWindow = false},{ showMainWindow = false;showSecondWindow = true})
    }

    if (showSecondWindow) {
        SecondaryWindow(icon, mainWindowState,{ showSecondWindow = false },{showMainWindow = true; showSecondWindow = false})
    }

    if (!showMainWindow && !showSecondWindow) {
        exitApplication()
    }
}

@Composable
fun MainWindow(
    icon:BitmapPainter,
    windowState: WindowState,
    onClose: () -> Unit,
    onClickSecondWindow: () -> Unit
){
    Window(
        onCloseRequest = onClose,
        title = "Ventana Principal",
        icon = icon,
        state = windowState
    ) {
        Button(onClick = onClickSecondWindow) {
            Text("Abrir Ventana Secundaria y cerrar esta")
        }
    }
}

@Composable
fun SecondaryWindow(
    icon:BitmapPainter,
    windowState: WindowState,
    onClose: () -> Unit,
    onClickSecondWindow: () -> Unit
){
    Window(
        onCloseRequest = onClose,
        title = "Ventana Secundaria",
        icon = icon,
        state = windowState
    ) {
        Button(onClick = onClickSecondWindow) {
            Text("Abrir Ventana Principal y cerrar esta")
        }
    }
}
