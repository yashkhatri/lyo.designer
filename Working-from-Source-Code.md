The instructions below help you setup an eclipse environment for
developing or testing the Lyo plugins from the latest available source
code.

Besides the OSLC4J SDK, the Lyo project contains 2 eclipse plugins that
help you develop OSLC-based applications:

1.  The **Lyo Modeller** - allows you to graphically model an
    oslc4j-based toolchain.
2.  The **Lyo Code Generator** - generates java code based on the
    toolchain model.

The instructions below support three setups that depend on which aspect
of the plugins you plan to test or develop:

-   **Scenario1** - Testing or using the Lyo plugins based on the latest
    versions of the source code
-   **Scenario2** - Developing the Lyo Modeller plugin (with less
    concern about the code generation)
-   **Scenario3** - Developing the Code Generator plugin (with less
    concern about the graphical modelling capabilities)

To simply use the latest plugin release, the reader is referred to the
[Toolchain Designer workshop on Modelling and Code
Generation](User-Manual-for-Toolchain-Modelling)
instead.

Eclipse Setup
=============

If you desire to work with the source code of the modelling tool, the
following once-only installation and configuration of your Eclipse
environment are required:

1.  Make sure your environment is setup for OSLC4J development as
    instructed on [General Setup for OSLC4J
    development](https://wiki.eclipse.org/Lyo/General_Setup_for_OSLC4J_Development)
2.  Uninstall any current Lyo plugins from your Eclipse workspace
    -   To avoid confusions, it is recommended to run different Eclipse
        workspaces for Lyo plugin binaries, and running from source
        code.
3.  Install the following additional Eclipse packages
    1.  Acceleo
    2.  Sirius Specifier Environment
    3.  A suitable EMF editor such as "EMF Facet SDK"\* or *"EMF -
        Eclipse Modeling Framework SDK"* .
    4.  Eclipse Plugin Development Environment
4.  Clone the OSLC Tools repository
    <git://git.eclipse.org/gitroot/lyo/org.eclipse.lyo.tools.git>
5.  View the Git repository in Eclipse
    1.  Open the Git Repositories perspective in Eclipse
    2.  Click the ***Add an existing local Git repository*** icon.
    3.  Select the *org.eclipse.lyo.tools* repository
    4.  The repository should now show up in your Git Repositories view
6.  Import the necessary Eclipse projects from the Git repository
    1.  In the Git Repositories view, right click the
        org.eclipse.lyo.tools repository and select ***Import
        Projects***
    2.  Select the ***Import Existing Projects*** wizard and click
        ***next***
    3.  Select all projects.
7.  Create a new *Eclipse Application* configuration called **Tool Chain
    Design**
    1.  Select **Run \> Run Configurations ...**
    2.  Select **Eclipse Application**
    3.  Press **New** button to create a new configuration
    4.  In the new dialog,
        1.  Set **name** to *Tool Chain Design*
        2.  In Arguments tab, change **VM argument** value to *-Xms256m
            -Xmx768m*

Testing or using the Lyo plugins
================================

To test or use the Lyo plugins based on the latest versions of the
source code:

1.  Run the *Tool Chain Design* configuration (created above) in order
    to run a new Eclipse workspace with the required plugins
    1.  Select **Run \> Run Configurations ...**
    2.  Select the *Tool Chain Design* configuration
    3.  Press **Run**
2.  In the new *Tool Chain Design* Eclipse workspace, switch to the
    **Sirius** perspective
3.  Proceed with the modelling and generation as instructed in the
    [Toolchain Modelling And Code Generation
    Workshop](Lyo/ToolchainModellingAndCodeGenerationWorkshop#Create_a_Toolchain_Modelling_Project "wikilink")

Developing the Lyo Modeller plugin
==================================

To develop the Lyo Modeller plugin (with less concern about the code
generation):

1.  Run the *Tool Chain Design* configuration (created above)
2.  create a Toolchain Modelling project as instructed in the [Testing
    or using the Lyo
    plugins](Lyo/modelling_and_generation/working_from_source_code#Testing_or_using_the_Lyo_plugins "wikilink")
    section above.
3.  Switch to the **Sirius** perspective
4.  Import the *org.eclipse.lyo.tools.toolchain.design* eclipse project
    into the Eclipse workspace from the OSLC Tools git repository.
5.  open and modify the *description/ToolChainModel.odesign* file as
    necessary.
    -   You can now see the effects of your changes directly on any
        opened Lyo Modelling views.

Developing the Code Generator plugin
====================================

To develop the Code Generator plugin (with less concern about the
graphical modelling capabilities):

1.  In the primary Eclipse workspace, modify the Acceleo template files
    in the project *org.eclipse.lyo.tools.oslc4emf.codegenerator* as
    necessary.
2.  Build the generator by selecting the menu item **Project \--\> Build
    Project**

Configure and run the adaptor generator
---------------------------------------

To run the code generator from soure code, the following steps are
needed:

1.  Define a "run configuration" for adaptor code generation
    1.  Select **Run \--\> Run Configurations\...**
    2.  Select *Acceleo Application*
    3.  Press the **New launch configuration** button
        1.  **Name**: something practical such as *Generate
            YourProviderProjectName*
        2.  **Project**: select the
            *org.eclipse.lyo.oslc4j.codegenerator* project
        3.  **Main class**:
            *org.eclipse.lyo.oslc4j.codegenerator.main.Generate*
        4.  **Model**: the newly created *adaptormodel* in the
            *YourProviderProjectName* project
        5.  **Target**: The YourProviderProjectName project
        6.  **Runner**: Acceleo Plug-in Application
    4.  Press **Apply**
    5.  Press **Close**.
2.  Once you are satisfied with your model, you can generate the adaptor
    code:
    1.  Select **Run \> Run Configurations ...**
    2.  Select the *Generate YourProviderProjectName* configuration
    3.  Press **Run**

Create a new adaptorInterface model
-----------------------------------

To test the code generator changes, you can run an Acceleo generation
configuration on any toolchain model. In most cases, it is more
convenient to create the models graphically using the existing Lyo
plugins - using another Eclipse workspace with the plugins installed.

You can however create and modify an adaptorInterface model within your
source code environment, with the following steps:

1.  Open the *adaptorInterface.ecore* model in the
    org.eclipse.lyo.oslc4j.adaptormodel project (folder *model*)
2.  Expand the ecore model until the *AdaptorInterface* EClass
3.  Right-click on the AdaptorInterface EClass & select **Create Dynamic
    Instance\...**
4.  In the dialog that appears
    1.  select the folder *adaptorModel* under the
        *YourProviderProjectName* project as the "parent folder"
    2.  choose a suitable File name for the adaptor model -
        *AdaptorInterface.xmi* is fine.
5.  Right-click on the newly created *AdaptorInterface.xmi* file, and
    select **open with \--\> Other\... \--\> EMF Facet Model Browser**.

Problems when composing models
------------------------------

Did you compose your model from other models and reference some of its
elements in your adaptor model? Currently, the way EMF models reference
each other in the xmi files causes problems with the code generator.
Before you trigger the code generator, perform the following steps:
(These steps need to be repeated if the adaptor model is later changed
to include further references).

1.  Copy the workspace location of any loaded resources (EMF models)
    1.  In the *Project Explorer* window, right-click on the
        *AdaptorInterface.xmi* file, and select **open with \--\> Other
        \... \--\> Sample Reflective Ecore Model Editor**.
    2.  In the newly opened window, right-click and select the context
        menu **Load Resource ...**
    3.  Select **Browse Workspace\...**
    4.  Select the domain specification EMF model that you have earlier
        loaded.
    5.  Copy the path to the xmi file selected into the clipboard (it
        will be something like
        *<platform:/resource/org.eclipse.lyo.oslc4j.adaptormodel/model/someSpecification.xmi>*)
    6.  Press **Cancel**.
2.  In the Project Explorer window, right-click on the
    *AdaptorInterface.xmi* file, and select **open with \--\> XML
    Editor**
3.  Search and replace each *href* entry that refers to your domain
    specification.
    1.  Replace href values that look like
        *href=\"../../org.eclipse.lyo.oslc4j.adaptormodel/model/someSpecification.xmi\#//\...*
        with the copied value (which ought to look more like
        *href="platform:/resource/org.eclipse.lyo.oslc4j.adaptormodel/model/someSpecification.xmi\#//\..."*).
4.  Repeat these steps for each domain specification model you have
    loaded and referenced.

Problems opening an Adaptor Model using the EMF editor
------------------------------------------------------

When running from source, the metamodels are not available in the
Eclipse workspace, which causes errors when trying to open the models
using an EMF editor.

Although the models can be used to generate code from, it may sometimes
be desired to edit them beforehand.

Edit the models and manually add the pathes as follows:

    &lt;oslc4j_tc:Toolchain ….. xsi:schemaLocation=&quot;http://org.eclipse.lyo/oslc4j/toolChain ../org.eclipse.lyo.oslc4j.adaptormodel/model/toolchain.ecore&quot; …&gt;

    &lt;adaptorInterfaces xsi:schemaLocation=&quot;http://org.eclipse.lyo/oslc4j/adaptorInterface ../org.eclipse.lyo.oslc4j.adaptormodel/model/adaptorInterface.ecore&quot; name=&quot;Testing Tool&quot; …
