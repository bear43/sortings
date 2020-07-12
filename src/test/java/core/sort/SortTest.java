package core.sort;

import core.sort.bubble.BubbleSort;
import core.sort.interfaces.ISort;
import core.sort.interfaces.ISortState;
import core.sort.util.CubeDrawer;
import core.sort.util.FastSort;
import core.sort.util.SlowSort;
import core.sort.util.shape.Cube2D;
import core.sort.util.vector.Vector2f;
import lombok.NonNull;
import org.junit.jupiter.api.*;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;
import org.opentest4j.AssertionFailedError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Logger;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@ExtendWith(TimingExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class SortTest {

    private static final Integer EVEN_ARRAY_SIZE = 10;

    private static final Integer ODD_ARRAY_SIZE = EVEN_ARRAY_SIZE + 1;

    private final Integer[] evenOriginalArray = new Integer[EVEN_ARRAY_SIZE];

    private final Integer[] evenUnsortedArray = new Integer[EVEN_ARRAY_SIZE];

    private final Integer[] oddOriginalArray = new Integer[ODD_ARRAY_SIZE];

    private final Integer[] oddUnsortedArray = new Integer[ODD_ARRAY_SIZE];

    private final long sleepTime = 500;

    private static final Logger logger = Logger.getLogger("SORTING");

    private long window;

    @Autowired
    @SlowSort
    private List<AbstractSort<Integer>> slowSortAlgs;

    @Autowired
    @FastSort
    private List<AbstractSort<Integer>> fastSortAlgs;

    private static void assertSorted(Integer[] array, Integer[] originalArray) {
        for(int i = 0; i < array.length; i++) {
            if(!array[i].equals(originalArray[i])) {
                throw new AssertionFailedError("Array should be sorted");
            }
        }
    }

    private void generateArrays(final Integer[] originalArray, final Integer[] unsortedArray, final Integer size) {
        for(int i = 0; i < size; i++) {
            originalArray[i] = i;
        }
        List<Integer> list = new ArrayList<>(Arrays.asList(originalArray));
        Collections.shuffle(list);
        Integer[] shuffledArray =  list.toArray(new Integer[0]);
        System.arraycopy(shuffledArray, 0, unsortedArray, 0, size);
    }

    private void init() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");

        // Configure GLFW
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

        // Create the window
        window = glfwCreateWindow(300, 300, "Hello World!", NULL, NULL);
        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
        });

        // Get the thread stack and push a new frame
        try ( MemoryStack stack = stackPush() ) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(window, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        } // the stack frame is popped automatically

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);
    }

    private void preRender() {
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

        // Set the clear color
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
    }

    private void render(Consumer<Void> renderHook) {

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        if ( !glfwWindowShouldClose(window) ) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
            renderHook.accept(null);
            glfwSwapBuffers(window); // swap the color buffers

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }

    private void closeGraphics() {
        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    

    @BeforeAll
    public void beforeAll() {
        generateArrays(evenOriginalArray, evenUnsortedArray, EVEN_ARRAY_SIZE);
        logger.info(String.format("Even array of %d elements", EVEN_ARRAY_SIZE));
        generateArrays(oddOriginalArray, oddUnsortedArray, ODD_ARRAY_SIZE);
        logger.info(String.format("Odd array of %d elements", ODD_ARRAY_SIZE));
        init();
        preRender();
    }

    private void sort(final List<? extends AbstractSort<Integer>> sortAlgs, final Integer[] array, final Integer[] originalArray) {
        long time;
        Consumer<ISortState> iSortStateConsumer = state -> {
            render(ignored -> {
                CubeDrawer.drawCubes(state);
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        };
        for(AbstractSort<Integer> alg : sortAlgs) {
            alg.subscribe(iSortStateConsumer);
            time = System.currentTimeMillis();
            Integer[] sortedArray = alg.sortWithAllocation(array);
            assertSorted(sortedArray, originalArray);
            time = System.currentTimeMillis() - time;
            logger.info(String.format("Algorithm [%s] took %d ms", alg.getName(), time));
            alg.informer.unsubscribe(iSortStateConsumer);
        }
    }

    public void slowEvenSort() {
        sort(slowSortAlgs, evenUnsortedArray, evenOriginalArray);
    }

    public void slowOddSort() {
        sort(slowSortAlgs, oddUnsortedArray, oddOriginalArray);
    }

    @Test
    @DisplayName("Slow algs test with EVEN and ODD array size")
    @Order(0)
    public void slowSort() {
        logger.info("EVEN SORTING");
        slowEvenSort();
        logger.info("ODD SORTING");
        slowOddSort();
    }

    @Test
    @DisplayName("Fast algs test with EVEN and ODD array size")
    @Order(1)
    public void fastSort() {
        logger.info("EVEN SORTING");
        fastEvenSort();
        logger.info("ODD SORTING");
        fastOddSort();
    }


    public void fastEvenSort() {
        sort(fastSortAlgs, evenUnsortedArray, evenOriginalArray);
    }


    public void fastOddSort() {
        sort(fastSortAlgs, oddUnsortedArray, oddOriginalArray);
    }


}