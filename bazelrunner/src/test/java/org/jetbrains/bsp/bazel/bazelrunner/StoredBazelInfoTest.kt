package org.jetbrains.bsp.bazel.bazelrunner

import io.kotest.matchers.shouldBe
import org.jetbrains.bsp.bazel.utils.dope.DopeTemp
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class StoredBazelInfoTest {

  @Test
  fun `should return null for not existing bazel info cache`() {
    // given
    val path = DopeTemp.createTempFile("bazel-info-cache-test.json")
    val storage = BazelInfoStorage(path)

    // when
    val empty = storage.load()

    // then
    empty shouldBe null
  }

  @Test
  fun `should store and load bazel info cache`() {
    // given
    val path = DopeTemp.createTempFile("bazel-info-cache-test.json")
    val storage = BazelInfoStorage(path)

    // when
    val bazelInfo = BasicBazelInfo(
        "/private/var/tmp/_bazel/125c7a6ca879ed16a4b4b1a74bc5f27b/execroot/bazel_bsp",
        Paths.get("/Users/user/workspace/bazel-bsp"),
        BazelRelease(6))

    storage.store(bazelInfo)
    val loaded = storage.load()

    // then
    loaded shouldBe bazelInfo
  }
}
