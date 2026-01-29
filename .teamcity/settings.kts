import jetbrains.buildServer.configs.kotlin.*

version = "2025.11"

project {
    buildType(Source)
    val numProjects=1
    val numConfigurationsPerProject=200
    for (i in 1..numProjects) {
        subProject {
            id("subProj_$i")
            name = "subProj $i"

         for (j in 1..numConfigurationsPerProject) {
                buildType {
                    id("subProj_bt_$i" + "_$j")
                    name = "bt $i $j"}

         }
        }
     }
}

object Source : BuildType({
    val numProjects = 1
    val numConfigurationsPerProject = 900

    name = "source"

    for (i in 1..numProjects) {
        for (j in 1..numConfigurationsPerProject) {
            dependencies {
                snapshot(id("subProj_bt_${i}_$j")) {
                    reuseBuilds = ReuseBuilds.NO
                }

            }
        }
    }
})


